package com.oliva.antonio.domain.usecase.event

import com.oliva.antonio.domain.repository.EventRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.runners.MockitoJUnitRunner

/**
 * Created by antonio
 */

@RunWith(MockitoJUnitRunner::class)
class GetEventTest {

    @Mock
    lateinit var eventRepository: EventRepository

    lateinit var getEvent: GetEvent

    @Before
    fun before() {
        getEvent = GetEvent(eventRepository)
    }

    @Test
    fun Given_UsecaseInvoked_When_UsecaseIsExecuted_Then_DataIsRetrievedFromRepository() {
        val params = 1
        getEvent.buildUseCaseObservable(params)

        verify(eventRepository, times(1)).getEvent(1)
    }
}