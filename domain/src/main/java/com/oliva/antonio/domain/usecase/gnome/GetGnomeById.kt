package com.oliva.antonio.domain.usecase.gnome

import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.repository.EventRepository
import com.oliva.antonio.domain.usecase.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Get gnome by id
 * Created by antoniojoseolivafaura on 07/12/2017.
 */
class GetGnomeById @Inject constructor(val eventRepository: EventRepository) : UseCase<Event, Int>() {

    override fun buildUseCaseObservable(params: Int): Flowable<Event> {
        return eventRepository.getGnomeById(params)
    }
}