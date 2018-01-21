package com.oliva.antonio.data.cache.room.event

import com.oliva.antonio.data.cache.room.event.session.mapSessionCacheEntityToSessionEntity
import com.oliva.antonio.data.entity.event.EventEntity

/**
 * Created by antonio on 1/21/18.
 */

fun mapEventEntityToEventCacheEntity(eventEntity: EventEntity) =
        EventCacheEntity(eventEntity.id,
                eventEntity.name,
                eventEntity.imageUrl,
                eventEntity.dateBegin,
                eventEntity.dateFinish,
                eventEntity.circuitFlag,
                cacheLastUpdate = System.currentTimeMillis())

fun mapEventCacheEntityToEventEntity(eventCacheEntity: EventCacheEntity) =
        EventEntity(eventCacheEntity.id,
                eventCacheEntity.name,
                eventCacheEntity.imageUrl,
                eventCacheEntity.dateBegin,
                eventCacheEntity.dateFinish,
                eventCacheEntity.circuitFlag,
                emptyList(),
                eventCacheEntity.cacheLastUpdate)

fun mapEventCacheWithSessionsToEventEntity(eventCacheWithSessions: EventCacheWithSessions) =
        EventEntity(eventCacheWithSessions.event.id,
                eventCacheWithSessions.event.name,
                eventCacheWithSessions.event.imageUrl,
                eventCacheWithSessions.event.dateBegin,
                eventCacheWithSessions.event.dateFinish,
                eventCacheWithSessions.event.circuitFlag,
                eventCacheWithSessions.sessions?.map { mapSessionCacheEntityToSessionEntity(it) },
                eventCacheWithSessions.event.cacheLastUpdate)