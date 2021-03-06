package com.oliva.antonio.data.network.entity.event

import com.oliva.antonio.data.entity.event.EventEntity
import com.oliva.antonio.data.network.entity.event.session.DetailApiResult

/**
 * Created by antonio
 */
internal fun mapApiResultToEventEntityList(eventsApiResult: EventsApiResult): List<EventEntity> =
        eventsApiResult.events.map {
            EventEntity(it.id,
                    if (it.name.isNullOrEmpty()) "" else it.name!!,
                    if (it.imageUrl.isNullOrEmpty()) "" else it.imageUrl!!,
                    if (it.dateBegin.isNullOrEmpty()) "" else it.dateBegin!!,
                    if (it.dateFinish.isNullOrEmpty()) "" else it.dateFinish!!,
                    if (it.circuitFlag.isNullOrEmpty()) "" else it.circuitFlag!!,
                    emptyList())
        }