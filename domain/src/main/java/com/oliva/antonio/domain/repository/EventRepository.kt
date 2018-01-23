package com.oliva.antonio.domain.repository

import com.oliva.antonio.domain.entity.Event
import io.reactivex.Flowable

/**
 * Events repository
 * Created by antonio
 */
interface EventRepository {

    fun getEvents(): Flowable<List<Event>>

    fun getEvent(id: Int): Flowable<Event>
}
