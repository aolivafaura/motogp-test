package com.oliva.antonio.dornatest.ui.main.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.oliva.antonio.brastlewarkguide.ui.common.ViewState
import com.oliva.antonio.common.network.Connectivity
import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.usecase.event.GetEvent
import com.oliva.antonio.dornatest.entity.EventUI
import com.oliva.antonio.dornatest.entity.mapEventToEventUI
import com.oliva.antonio.dornatest.ui.BaseViewModel
import io.reactivex.subscribers.ResourceSubscriber

/**
 * Created by antonio on 1/21/18.
 */

class EventDetailViewModel(val getEvent: GetEvent, val connectivity: Connectivity) : BaseViewModel(connectivity) {

    // DATA ----------------------------------------------------------------------------------------
    val eventData = MutableLiveData<EventUI>()
    val viewState = MutableLiveData<ViewState>()

    // PUBLIC METHODS ------------------------------------------------------------------------------
    fun loadEvent(id: Int) {
        checkConnectivity()

        viewState.value = ViewState.Refreshing
        getEvent.execute(object : ResourceSubscriber<Event>() {
            override fun onComplete() {

            }

            override fun onNext(event: Event?) {
                viewState.value = ViewState.Idle
                event?.let {
                    eventData.value = mapEventToEventUI(event)
                }
                dispose()
            }

            override fun onError(t: Throwable?) {
                dispose()
            }
        }, id)
    }

    // VIEW MODEL OVERRIDES ------------------------------------------------------------------------
    override fun onCleared() {
        getEvent.dispose()
    }

    // FACTORY -------------------------------------------------------------------------------------
    class DetailViewModelFactory(private val getEvent: GetEvent,
                                 private val connectivity: Connectivity) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EventDetailViewModel(getEvent, connectivity) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}