package com.oliva.antonio.data.entity.event

import com.oliva.antonio.data.entity.event.session.mapSessionEntityToSession
import com.oliva.antonio.domain.entity.Event

/**
 * Created by antonio on 12/3/17.
 */

internal fun mapEventEntityToEvent(eventEntity: EventEntity) = Event(
        eventEntity.id,
        eventEntity.name,
        eventEntity.imageUrl,
        eventEntity.dateBegin,
        eventEntity.dateFinish,
        eventEntity.circuitFlag,
        eventEntity.sessions.map { mapSessionEntityToSession(it) })
