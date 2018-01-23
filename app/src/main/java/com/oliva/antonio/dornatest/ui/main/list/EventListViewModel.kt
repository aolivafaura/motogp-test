package com.oliva.antonio.dornatest.ui.main.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.oliva.antonio.brastlewarkguide.ui.common.ViewState
import com.oliva.antonio.common.network.Connectivity
import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.usecase.event.GetAllEvents
import com.oliva.antonio.dornatest.entity.EventUI
import com.oliva.antonio.dornatest.entity.mapEventToEventUI
import com.oliva.antonio.dornatest.ui.BaseViewModel
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Created by antonio on 1/21/18.
 */

class EventListViewModel(val getAllEvents: GetAllEvents,
                         val connectivity: Connectivity) : BaseViewModel(connectivity) {

    // DATA ----------------------------------------------------------------------------------------
    val eventsData = MutableLiveData<List<EventUI>>()
    val state = MutableLiveData<ViewState>()

    fun setViewState(state: ViewState) {
        this.state.value = state

        if (state == ViewState.Refreshing) {
            loadEvents()
        }
    }

    private fun loadEvents() {
        checkConnectivity()

        getAllEvents.execute(object : ResourceSubscriber<List<Event>>() {
            override fun onComplete() {}

            override fun onNext(events: List<Event>) {
                eventsData.value = events.map { mapEventToEventUI(it) }
                dispose()
                state.value = ViewState.Idle
            }

            override fun onError(t: Throwable?) {
                // TODO Antonio
            }
        }, null)
    }

    // VIEW MODEL OVERRIDES ------------------------------------------------------------------------
    override fun onCleared() {
        getAllEvents.dispose()
    }

    // FACTORY -------------------------------------------------------------------------------------
    class ListViewModelFactory(private val getAllEvents: GetAllEvents,
                               private val connectivity: Connectivity) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EventListViewModel(getAllEvents, connectivity) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}