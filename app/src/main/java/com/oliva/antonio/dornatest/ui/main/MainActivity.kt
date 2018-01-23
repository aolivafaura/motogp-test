package com.oliva.antonio.dornatest.ui.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.oliva.antonio.dornatest.R
import com.oliva.antonio.dornatest.ui.BaseActivity
import com.oliva.antonio.dornatest.ui.main.detail.EventDetailFragment
import com.oliva.antonio.dornatest.ui.main.list.EventListFragment
import dagger.Module
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    lateinit var mainViewModel: MainViewModel

    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(EventListFragment.newInstance())

        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        snackbar = Snackbar.make(container, R.string.no_connection, Snackbar.LENGTH_INDEFINITE)

        observeIsOnline()
        observeSelectedEvent()
    }

    private fun observeIsOnline() {
        mainViewModel.isOnline.observe(this, Observer<Boolean> {
            it?.let {
                if (it) {
                    if (snackbar.isShown) {
                        snackbar.dismiss()
                    }
                } else {
                    if (!snackbar.isShown) {
                        snackbar.show()
                    }
                }
            }
        })
    }

    private fun observeSelectedEvent() {
        mainViewModel.selectedEvent.observe(this, Observer<Int> {
            it?.let {
                replaceFragment(EventDetailFragment.newInstance(),
                        EventDetailFragment::class.java.simpleName,
                        true)
            }
        })
    }

    // DAGGER MODULE -------------------------------------------------------------------------------
    @Module
    class MainActivityModule
}
