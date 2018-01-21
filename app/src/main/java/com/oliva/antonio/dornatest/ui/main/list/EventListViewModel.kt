package com.oliva.antonio.dornatest.ui.main.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.oliva.antonio.brastlewarkguide.ui.common.ViewState
import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.usecase.event.GetAllEvents

/**
 * Created by antonio on 1/21/18.
 */

class EventListViewModel(val getAllEvents: GetAllEvents) : ViewModel() {

    // DATA ----------------------------------------------------------------------------------------
    val eventsData = MutableLiveData<MutableList<Event>>()
    val state = MutableLiveData<ViewState>()

    // VIEW MODEL OVERRIDES ------------------------------------------------------------------------
    override fun onCleared() {
        getAllEvents.dispose()
    }

    // FACTORY -------------------------------------------------------------------------------------
    class ListViewModelFactory(private val getAllEvents: GetAllEvents) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(EventListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EventListViewModel(getAllEvents) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}