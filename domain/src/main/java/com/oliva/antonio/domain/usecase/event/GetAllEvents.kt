package com.oliva.antonio.domain.usecase.event

import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.repository.EventRepository
import com.oliva.antonio.domain.usecase.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by antonio
 */
class GetAllEvents @Inject constructor(val eventRepository: EventRepository) : UseCase<List<Event>, Void?>() {
    override fun buildUseCaseObservable(params: Void?): Flowable<List<Event>> {
        return eventRepository.getEvents()
    }
}