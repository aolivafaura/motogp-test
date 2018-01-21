package com.oliva.antonio.domain.usecase.gnome

import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.repository.EventRepository
import com.oliva.antonio.domain.usecase.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Get all gnomes
 * Created by antonio on 05/12/2017.
 */
class GetAllGnomes @Inject constructor(val eventRepository: EventRepository) : UseCase<List<Event>, GetAllGnomes.Params>() {

    override fun buildUseCaseObservable(params: GetAllGnomes.Params): Flowable<List<Event>> {
        return eventRepository.getEvents(params.offset, params.forceRefresh)
    }

    /**
     * Use case params
     * @param offset       Initial offset for the query
     * @param forceRefresh Whether should retrieve data from server if possible
     */
    data class Params(val offset: Int, val forceRefresh: Boolean)
}
