package com.oliva.antonio.domain.usecase.gnome

import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.repository.EventRepository
import com.oliva.antonio.domain.usecase.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Get all gnomes in the list
 * Created by antonio on 12/6/17.
 */
class GetGnomesByName @Inject constructor(val eventRepository: EventRepository) : UseCase<List<Event>, List<String>>() {

    override fun buildUseCaseObservable(params: List<String>): Flowable<List<Event>> {
        return eventRepository.getEvent(params)
    }
}