package com.oliva.antonio.dornatest.ui.main

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by antonio on 1/21/18.
 */

class MainViewModel : ViewModel() {

    val isOnline = MutableLiveData<Boolean>()
}