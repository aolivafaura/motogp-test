package com.oliva.antonio.data.cache.room

import android.util.Log
import com.oliva.antonio.data.cache.CacheDao
import com.oliva.antonio.data.cache.room.event.EventCacheDao
import com.oliva.antonio.data.cache.room.event.mapEventCacheEntityToEventEntity
import com.oliva.antonio.data.cache.room.event.mapEventCacheWithSessionsToEventEntity
import com.oliva.antonio.data.cache.room.event.mapEventEntityToEventCacheEntity
import com.oliva.antonio.data.cache.room.event.session.SessionCacheEntity
import com.oliva.antonio.data.cache.room.event.session.mapSessionEntityToSessionCacheEntity
import com.oliva.antonio.data.entity.event.EventEntity
import io.reactivex.Flowable

/**
 * Created by antonio on 12/3/17.
 */

class EventCacheDaoWrapper(val eventCacheDao: EventCacheDao) : CacheDao<EventEntity> {

    override fun insert(entities: List<EventEntity>): List<Long> {
        val sessionsToInsert: List<SessionCacheEntity> =
                entities.filter { it.sessions.isNotEmpty() }
                        .flatMap { event -> event.sessions.map { mapSessionEntityToSessionCacheEntity(it, event.id) } }

        if (sessionsToInsert.isNotEmpty()) {
            val count = eventCacheDao.insertSessions(sessionsToInsert)
            Log.d("sd", count.toString())
        }

        return eventCacheDao.insertEvents(entities.map { mapEventEntityToEventCacheEntity(it) })
    }

    override fun get(id: Int): Flowable<EventEntity> = eventCacheDao.get(id).map { mapEventCacheWithSessionsToEventEntity(it) }

    override fun getAll(): Flowable<List<EventEntity>> = eventCacheDao.getAll().map { it.map { mapEventCacheEntityToEventEntity(it) } }
}
