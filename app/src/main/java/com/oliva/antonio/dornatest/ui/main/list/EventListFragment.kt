package com.oliva.antonio.dornatest.ui.main.list

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.oliva.antonio.domain.usecase.event.GetAllEvents
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

class EventListFragment : BaseFragment() {

    // Variable to check fragment recreations through back stack pops
    private var beingRecreated = false

    @Inject
    lateinit var viewModelFactory: EventListViewModel.ListViewModelFactory
    lateinit var listViewModel: EventListViewModel
    lateinit var mainViewModel: MainViewModel

    // BASE FRAGMENT OVERRIDES ---------------------------------------------------------------------
    override fun getFragmentLayout() = R.layout.fragment_event_list

    // LIFECYCLE OVERRIDES -------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(EventListViewModel::class.java)
    }

    // PRIVATE METHODS -----------------------------------------------------------------------------
    // Views ---------------------------------------------------------------------------------------

    // Callbacks -----------------------------------------------------------------------------------

    // Observers -----------------------------------------------------------------------------------

    // COMPANION OBJECT ----------------------------------------------------------------------------
    companion object {
        fun newInstance() = EventListFragment()
    }

    // DAGGER MODULE -------------------------------------------------------------------------------
    @Module
    class EventListFragmentModule {
        @Provides
        fun provideListViewModelFactory(getAllEvents: GetAllEvents) = EventListViewModel.ListViewModelFactory(getAllEvents)

    }

    // DAGGER PROVIDER -----------------------------------------------------------------------------
    @Module
    abstract class ListFragmentProvider {
        @ContributesAndroidInjector(modules = [(EventListFragmentModule::class)])
        abstract fun provideEventListFragmentFactory(): EventListFragment
    }
}