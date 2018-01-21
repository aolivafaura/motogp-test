package com.oliva.antonio.dornatest.ui.main.detail

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.oliva.antonio.domain.usecase.event.GetEvent
import com.oliva.antonio.dornatest.R
import com.oliva.antonio.dornatest.ui.BaseFragment
import com.oliva.antonio.dornatest.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import javax.inject.Inject

/**
 * Created by antonio on 1/21/18.
 */

class EventDetailFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: EventDetailViewModel.DetailViewModelFactory
    lateinit var eventDetailViewModel: EventDetailViewModel
    lateinit var mainViewModel: MainViewModel

    // BASE FRAGMENT OVERRIDES ---------------------------------------------------------------------
    override fun getFragmentLayout() = R.layout.fragment_event_list

    // LIFECYCLE OVERRIDES -------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        eventDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(EventDetailViewModel::class.java)
    }

    // PRIVATE METHODS -----------------------------------------------------------------------------
    // Views ---------------------------------------------------------------------------------------

    // Callbacks -----------------------------------------------------------------------------------

    // Observers -----------------------------------------------------------------------------------

    // COMPANION OBJECT ----------------------------------------------------------------------------
    companion object {
        fun newInstance() = EventDetailFragment()
    }

    // DAGGER MODULE -------------------------------------------------------------------------------
    @Module
    class EventDetailFragmentModule {
        @Provides
        fun provideDetailViewModelFactory(getEvent: GetEvent) = EventDetailViewModel.DetailViewModelFactory(getEvent)
    }

    // DAGGER PROVIDER -----------------------------------------------------------------------------
    @Module
    abstract class DetailFragmentProvider {
        @ContributesAndroidInjector(modules = [(EventDetailFragmentModule::class)])
        abstract fun provideEventDetailFragmentFactory(): EventDetailFragment
    }
}