package com.oliva.antonio.data.cache.room.event.session

import com.oliva.antonio.data.entity.event.session.SessionEntity

/**
 * Created by antonio on 1/21/18.
 */

fun mapSessionEntityToSessionCacheEntity(sessionEntity: SessionEntity, eventId: Int) =
        SessionCacheEntity(0,
                sessionEntity.id,
                sessionEntity.name,
                sessionEntity.champName,
                sessionEntity.startTime,
                sessionEntity.endTime,
                eventId,
                cacheLastUpdate = System.currentTimeMillis())

fun mapSessionCacheEntityToSessionEntity(sessionCacheEntity: SessionCacheEntity) =
        SessionEntity(sessionCacheEntity.id,
                sessionCacheEntity.name,
                sessionCacheEntity.champName,
                sessionCacheEntity.startTime,
                sessionCacheEntity.endTime,
                sessionCacheEntity.cacheLastUpdate)