package com.oliva.antonio.data.repository

import android.util.Log
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
 * This class is in charge to decide the source of the data. In this case, just network and room
 * library cache are present, and {@see com.oliva.antonio.brastlewarkguide.network.LocalCacheInterceptor}
 * do the hard work, so is easy in this case to choose between sources.
 *
 * Created by antonio on 12/3/17.
 */
class EventDataRepository(val eventCacheDao: CacheDao<EventEntity>,
                          val dornaService: DornaService,
                          val connectivity: Connectivity) : EventRepository {

    override fun getEvents(): Flowable<List<Event>> {
        return if (connectivity.isConnected()) {
            Flowable.fromPublisher(eventCacheDao.getAll())
                    .flatMap {
                        Log.d("CACHE", "EEEEEH")
                        if (it.isNotEmpty() && it.none { isOutdated(it) }) {
                            Log.d("CACHE", "from cache")
                            Flowable.just<List<EventEntity>>(it)
                        } else {
                            Log.d("CACHE", "from network")
                            dornaService.getEvents().map { mapApiResultToEventEntityList(it) }
                                    .doOnNext { eventCacheDao.insert(it) }
                        }
                    }
                    .map { it.map { mapEventEntityToEvent(it) } }
        } else {
            eventCacheDao.getAll().map { it.map { mapEventEntityToEvent(it) } }
        }
    }

    override fun getEvent(id: Int): Flowable<Event> {
        return if (connectivity.isConnected()) {
            Flowable.fromPublisher(eventCacheDao.get(id))
                    .flatMap {
                        if (!isOutdated(it)) {
                            Flowable.just<EventEntity>(it)
                        } else {
                            dornaService.getEvent(id).map { mapDetailApiResultToEventEntity(it) }
                                    .doOnNext { eventCacheDao.insert(singletonList(it)) }
                        }
                    }
                    .map { mapEventEntityToEvent(it) }
        } else {
            eventCacheDao.get(id).map { mapEventEntityToEvent(it) }
        }
    }

    private fun isOutdated(cachedEvents: EventEntity): Boolean =
            System.currentTimeMillis() - cachedEvents.cacheLastUpdate > 15000
}
