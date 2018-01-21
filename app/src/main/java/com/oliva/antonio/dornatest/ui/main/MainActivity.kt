package com.oliva.antonio.dornatest.ui.main

import android.os.Bundle
import com.oliva.antonio.dornatest.R
import com.oliva.antonio.dornatest.ui.BaseActivity
import dagger.Module

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // DAGGER MODULE -------------------------------------------------------------------------------
    @Module
    class MainActivityModule
}
