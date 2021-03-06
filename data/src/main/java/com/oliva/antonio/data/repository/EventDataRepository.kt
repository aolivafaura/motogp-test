package com.oliva.antonio.data.repository

import com.oliva.antonio.common.network.Connectivity
import com.oliva.antonio.data.cache.CacheDao
import com.oliva.antonio.data.entity.event.EventEntity
import com.oliva.antonio.data.entity.event.mapEventEntityToEvent
import com.oliva.antonio.data.network.DornaService
import com.oliva.antonio.data.network.entity.event.mapApiResultToEventEntityList
import com.oliva.antonio.data.network.entity.event.session.mapDetailApiResultToEventEntity
import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.repository.EventRepository
import io.reactivex.Flowable
import java.util.Collections.singletonList

/**
 * EventEntity repository implementation.
 *
 * Created by antonio
 */
class EventDataRepository(val eventCacheDao: CacheDao<EventEntity>,
                          val dornaService: DornaService,
                          val connectivity: Connectivity) : EventRepository {

    override fun getEvents(): Flowable<List<Event>> {
        return if (connectivity.isConnected()) {
            eventCacheDao.getAll()
                    .flatMap { eventList ->
                        if (eventList.isNotEmpty() && eventList.none { isOutdated(it) }) {
                            Flowable.just<List<EventEntity>>(eventList)
                        } else {
                            dornaService.getEvents().map { mapApiResultToEventEntityList(it) }
                                    .doOnNext { eventCacheDao.insert(it) }
                                    .onErrorReturn { eventList }
                        }
                    }
                    .map { it.map { mapEventEntityToEvent(it) } }
        } else {
            eventCacheDao.getAll().map { it.map { mapEventEntityToEvent(it) } }
        }
    }

    override fun getEvent(id: Int): Flowable<Event> {
        return if (connectivity.isConnected()) {
            eventCacheDao.get(id)
                    .flatMap { event ->
                        if (isOutdated(event) || event.sessions.isEmpty()) {
                            dornaService.getEvent(id).map { mapDetailApiResultToEventEntity(it) }
                                    .doOnNext { eventCacheDao.insert(singletonList(it)) }
                                    .onErrorReturn { event }
                        } else {
                            Flowable.just<EventEntity>(event)
                        }
                    }
                    .map { mapEventEntityToEvent(it) }
        } else {
            eventCacheDao.get(id).map { mapEventEntityToEvent(it) }
        }
    }

    private fun isOutdated(cachedEvents: EventEntity): Boolean =
            System.currentTimeMillis() - cachedEvents.cacheLastUpdate > CACHE_ALIVE
}
