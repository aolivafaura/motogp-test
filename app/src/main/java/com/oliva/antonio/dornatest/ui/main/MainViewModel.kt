package com.oliva.antonio.dornatest.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by antonio
 */

class MainViewModel : ViewModel() {

    val isOnline = MutableLiveData<Boolean>()
    val selectedEvent = MutableLiveData<Int>()

    fun onSelectedEvent(id: Int) {
        selectedEvent.value = id
    }
}