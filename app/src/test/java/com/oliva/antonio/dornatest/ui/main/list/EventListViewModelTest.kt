package com.oliva.antonio.dornatest.ui.main.list

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.anyOrNull
import com.oliva.antonio.common.network.Connectivity
import com.oliva.antonio.domain.usecase.event.GetAllEvents
import com.oliva.antonio.dornatest.ui.common.ViewState
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

/**
 * I wish I could write more tests and more exhaustively, but I don't want to extend
 * too much the delivery date.
 * Created by antonio
 */
class EventListViewModelTest {

    // Bypass main thread on MutableLiveData objects
    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var getAllEvents: GetAllEvents

    @Mock
    lateinit var connectivity: Connectivity

    private lateinit var eventListViewModel: EventListViewModel

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this);

        eventListViewModel = EventListViewModel(getAllEvents, connectivity)
    }

    @Test
    fun Given_ViewStateChange_When_RefreshingIsSet_Then_EventsAreRetrieved() {
        eventListViewModel.setViewState(ViewState.Refreshing)

        verify(getAllEvents, times(1)).execute(any(), anyOrNull())
    }

    @Test
    fun Given_ViewStateChange_When_EventsAreBeingLoaded_Then_ConnectivityIsChecked() {
        eventListViewModel.setViewState(ViewState.Refreshing)

        verify(connectivity, times(1)).isConnected()
    }
}