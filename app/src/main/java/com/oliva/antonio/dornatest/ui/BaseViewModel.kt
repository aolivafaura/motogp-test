package com.oliva.antonio.dornatest.ui

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.oliva.antonio.common.network.Connectivity

/**
 * Created by antonio on 1/23/18.
 */

open class BaseViewModel(private val connectivity: Connectivity): ViewModel() {

    val isOnline = MutableLiveData<Boolean>()

    protected fun checkConnectivity() {
        isOnline.value = connectivity.isConnected()
    }
}