package com.oliva.antonio.domain.usecase.gnome

import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.repository.EventRepository
import com.oliva.antonio.domain.usecase.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Get gnomes filtered by name use case implementation
 * Created by antoniojoseolivafaura on 05/12/2017.
 */
class FilterGnomesByName @Inject constructor(val eventRepository: EventRepository) : UseCase<List<Event>, FilterGnomesByName.Params>() {

    override fun buildUseCaseObservable(params: FilterGnomesByName.Params): Flowable<List<Event>> {
        return eventRepository.getGnomesFilteredByName(params.offset, "%${params.name}%")
    }

    /**
     * Use case params
     * @param offset Initial offset for the query
     * @param name   Filter to apply
     */
    data class Params(val offset: Int, val name: String)
}
