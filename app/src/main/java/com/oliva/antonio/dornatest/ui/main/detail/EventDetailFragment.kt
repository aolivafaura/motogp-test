package com.oliva.antonio.dornatest.ui.main.detail

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.transition.TransitionManager
import android.widget.TextView
import com.oliva.antonio.brastlewarkguide.ui.common.ViewState
import com.oliva.antonio.common.network.Connectivity
import com.oliva.antonio.domain.usecase.event.GetEvent
import com.oliva.antonio.dornatest.R
import com.oliva.antonio.dornatest.entity.EventUI
import com.oliva.antonio.dornatest.entity.SessionUI
import com.oliva.antonio.dornatest.extensions.loadUrl
import com.oliva.antonio.dornatest.extensions.loadUrlRounded
import com.oliva.antonio.dornatest.ui.BaseFragment
import com.oliva.antonio.dornatest.ui.main.MainViewModel
import com.oliva.antonio.dornatest.utils.formatEventDates
import com.oliva.antonio.dornatest.utils.formatSessionDates
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import kotlinx.android.synthetic.main.fragment_event_detail.*
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
    override fun getFragmentLayout() = R.layout.fragment_event_detail

    // LIFECYCLE OVERRIDES -------------------------------------------------------------------------
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        mainViewModel = ViewModelProviders.of(activity!!).get(MainViewModel::class.java)
        eventDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(EventDetailViewModel::class.java)

        observeViewState()
        observeEventData()

        mainViewModel.selectedEvent.value?.let {
            eventDetailViewModel.loadEvent(mainViewModel.selectedEvent.value!!)
        }
    }

    // PRIVATE METHODS -----------------------------------------------------------------------------
    // Observers -----------------------------------------------------------------------------------
    private fun observeEventData() {
        eventDetailViewModel.eventData.observe(this, Observer<EventUI> {
            it?.let {
                fillData(it)
            }
        })
    }

    private fun fillData(event: EventUI) {
        ct_detail_event.title = event.name
        iv_event_image_detail.loadUrl(event.imageUrl)
        iv_flag_detail.loadUrlRounded(event.circuitFlag)
        tv_dates_detail.text = formatEventDates(event.dateBegin, event.dateFinish)
        fillSessions(event.sessions)
    }

    private fun fillSessions(sessions: List<SessionUI>) {
        TransitionManager.beginDelayedTransition(cl_detail)
        val constraintsSet = ConstraintSet()
        constraintsSet.clone(cl_detail)

        for (session in sessions) {
            val item = layoutInflater.inflate(R.layout.row_session_detail, null)
            item.findViewById<TextView>(R.id.tv_champ).text = session.champName
            item.findViewById<TextView>(R.id.tv_name).text = session.name
            item.findViewById<TextView>(R.id.tv_session_date).text = formatSessionDates(session.startTime, session.endTime)

            sessions_list.addView(item)
        }

        constraintsSet.applyTo(cl_detail)
    }

    private fun observeViewState() {
        eventDetailViewModel.viewState.observe(this, Observer<ViewState> {
            it?.let {
                when (it) {
                    ViewState.Refreshing -> {
                    }
                    ViewState.Idle -> {
                    }
                }
            }
        })
    }

    // COMPANION OBJECT ----------------------------------------------------------------------------
    companion object {
        fun newInstance() = EventDetailFragment()
    }

    // DAGGER MODULE -------------------------------------------------------------------------------
    @Module
    class EventDetailFragmentModule {
        @Provides
        fun provideDetailViewModelFactory(getEvent: GetEvent,
                                          connectivity: Connectivity) = EventDetailViewModel.DetailViewModelFactory(getEvent, connectivity)
    }

    // DAGGER PROVIDER -----------------------------------------------------------------------------
    @Module
    abstract class DetailFragmentProvider {
        @ContributesAndroidInjector(modules = [(EventDetailFragmentModule::class)])
        abstract fun provideEventDetailFragmentFactory(): EventDetailFragment
    }
}