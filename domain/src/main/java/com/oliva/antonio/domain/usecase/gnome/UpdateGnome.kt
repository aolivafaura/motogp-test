package com.oliva.antonio.domain.usecase.gnome

import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.repository.EventRepository
import com.oliva.antonio.domain.usecase.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Update gnome info
 * Created by antoniojoseolivafaura on 05/12/2017.
 */
class UpdateGnome @Inject constructor(val eventRepository: EventRepository) : UseCase<Event, Event>() {

    override fun buildUseCaseObservable(params: Event): Flowable<Event> {
        return eventRepository.updateGnome(params)
    }
}