package com.oliva.antonio.data.repository.gnome

import com.oliva.antonio.data.cache.CacheDao
import com.oliva.antonio.common.network.Connectivity
import com.oliva.antonio.data.entity.event.EventEntity
import com.oliva.antonio.data.network.DornaService
import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.repository.EventRepository
import io.reactivex.Flowable

/**
 * Event repository implementation.
 * This class is in charge to decide the source of the data. In this case, just network and room
 * library cache are present, and {@see com.oliva.antonio.brastlewarkguide.network.LocalCacheInterceptor}
 * do the hard work, so is easy in this case to choose between sources.
 *
 * Created by antonio on 12/3/17.
 */
class EventDataRepository(val cacheDao: CacheDao<EventEntity>,
                          val dornaService: DornaService,
                          val connectivity: Connectivity) : EventRepository {

    override fun getEvents(): Flowable<List<Event>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEvent(id: Int): Flowable<List<Event>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
