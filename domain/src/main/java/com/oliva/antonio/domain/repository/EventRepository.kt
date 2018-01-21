package com.oliva.antonio.domain.repository

import com.oliva.antonio.domain.entity.Event
import io.reactivex.Flowable

/**
 * Gnomes repository
 * Created by antonio on 12/3/17.
 */
interface EventRepository {

    /**
     *
     */
    fun getEvents(): Flowable<List<Event>>

    /**
     *
     */
    fun getEvent(id: Int): Flowable<List<Event>>
}
