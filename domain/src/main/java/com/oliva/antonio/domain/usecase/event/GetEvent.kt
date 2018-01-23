package com.oliva.antonio.domain.usecase.event

import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.repository.EventRepository
import com.oliva.antonio.domain.usecase.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by antonio
 */

class GetEvent @Inject constructor(val eventRepository: EventRepository) : UseCase<Event, Int>() {
    override fun buildUseCaseObservable(params: Int): Flowable<Event> {
        return eventRepository.getEvent(params)
    }
}