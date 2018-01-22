package com.oliva.antonio.dornatest.ui.main.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import com.oliva.antonio.brastlewarkguide.ui.common.ViewState
import com.oliva.antonio.brastlewarkguide.ui.main.list.adapter.EventsAdapter
import com.oliva.antonio.common.network.Connectivity
import com.oliva.antonio.domain.usecase.event.GetAllEvents
import com.oliva.antonio.dornatest.R
import com.oliva.antonio.dornatest.entity.EventUI
import com.oliva.antonio.dornatest.ui.BaseFragment
import com.oliva.antonio.dornatest.ui.main.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import kotlinx.android.synthetic.main.fragment_event_list.*
import javax.inject.Inject

/**
 * Created by antonio on 1/21/18.
 */

class EventListFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: EventListViewModel.ListViewModelFactory
    lateinit var listViewModel: EventListViewModel
    lateinit var mainViewModel: MainViewModel

    // VARIABLES -----------------------------------------------------------------------------------
    private var adapter: EventsAdapter? = null

    // BASE FRAGMENT OVERRIDES ---------------------------------------------------------------------
    override fun getFragmentLayout() = R.layout.fragment_event_list

    // LIFECYCLE OVERRIDES -------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        listViewModel = ViewModelProviders.of(this, viewModelFactory).get(EventListViewModel::class.java)

        listViewModel.setViewState(ViewState.Refreshing)

        observeEvents()
        observeViewState()
        observeIsOnline()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        if (listViewModel.eventsData.value?.isNotEmpty() == true) {
            if (rvlist.adapter == null) {
                initAdapter(listViewModel.eventsData.value!!.toMutableList())
            } else {
                adapter?.updateList(listViewModel.eventsData.value!!.toMutableList())
            }
        } else {
            listViewModel.setViewState(ViewState.Refreshing)
        }
    }

    // PRIVATE METHODS -----------------------------------------------------------------------------
    // Views ---------------------------------------------------------------------------------------
    private fun initViews() {
        swiperefresh.setOnRefreshListener(onRefreshAction())

        val layoutManager = LinearLayoutManager(this.context)
        rvlist.layoutManager = layoutManager
    }

    private fun initAdapter(items: MutableList<EventUI>) {
        adapter = EventsAdapter(items)
        adapter?.onClickItem = { onListItemClick(it) }

        rvlist.adapter = adapter
    }

    // Observers -----------------------------------------------------------------------------------
    private fun observeEvents() {
        listViewModel.eventsData.observe(this, Observer<List<EventUI>> {
            it?.let {
                adapter?.apply {
                    updateList(it.toMutableList())
                } ?: initAdapter(it.toMutableList())
            }
        })
    }

    private fun observeViewState() {
        listViewModel.state.observe(this, Observer<ViewState> {
            when (it) {
                ViewState.Refreshing -> swiperefresh.isRefreshing = true
                ViewState.Idle -> swiperefresh.isRefreshing = false
            }
        })
    }

    private fun observeIsOnline() {
        listViewModel.isOnline.observe(this, Observer<Boolean> {
            it?.let {
                mainViewModel.isOnline.value = it
            }
        })
    }

    // Callbacks -----------------------------------------------------------------------------------
    private fun onRefreshAction() = { listViewModel.setViewState(ViewState.Refreshing) }

    private fun onListItemClick(id: Int) = Log.d("LISTCLICK", id.toString())

    // COMPANION OBJECT ----------------------------------------------------------------------------
    companion object {
        fun newInstance() = EventListFragment()
    }

    // DAGGER MODULE -------------------------------------------------------------------------------
    @Module
    class EventListFragmentModule {
        @Provides
        fun provideListViewModelFactory(getAllEvents: GetAllEvents,
                                        connectivity: Connectivity) = EventListViewModel.ListViewModelFactory(getAllEvents, connectivity)

    }

    // DAGGER PROVIDER -----------------------------------------------------------------------------
    @Module
    abstract class ListFragmentProvider {
        @ContributesAndroidInjector(modules = [(EventListFragmentModule::class)])
        abstract fun provideEventListFragmentFactory(): EventListFragment
    }
}