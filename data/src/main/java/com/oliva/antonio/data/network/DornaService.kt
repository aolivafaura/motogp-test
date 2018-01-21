package com.oliva.antonio.data.network

import com.oliva.antonio.data.network.entity.event.session.DetailApiResult
import com.oliva.antonio.data.network.entity.event.EventsApiResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by antonio on 11/30/17.
 */
interface DornaService {

    @GET("en/motogpapp/events_list/")
    fun getEvents(): Flowable<EventsApiResult>

    @GET("en/motogpapp/events_list/{id}")
    fun getEvent(@Path("id") id: Int): Flowable<DetailApiResult>
}