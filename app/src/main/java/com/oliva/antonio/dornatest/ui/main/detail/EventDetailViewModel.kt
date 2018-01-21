package com.oliva.antonio.dornatest.ui.main.detail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.usecase.event.GetEvent

/**
 * Created by antonio on 1/21/18.
 */

class EventDetailViewModel(val getEvent: GetEvent) : ViewModel() {

    // DATA ----------------------------------------------------------------------------------------
    val eventData = MutableLiveData<MutableList<Event>>()

    // VIEW MODEL OVERRIDES ------------------------------------------------------------------------
    override fun onCleared() {
        getEvent.dispose()
    }

    // FACTORY -------------------------------------------------------------------------------------
    class DetailViewModelFactory(private val getEvent: GetEvent) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(com.oliva.antonio.dornatest.ui.main.list.EventListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return EventDetailViewModel(getEvent) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}