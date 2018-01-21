package com.oliva.antonio.data.cache.room.event

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Relation
import com.oliva.antonio.data.cache.room.event.session.SessionCacheEntity

/**
 * Created by antonio on 1/21/18.
 */

data class EventCacheWithSessions(@Embedded
                                  var event: EventCacheEntity) {

    @Relation(parentColumn = "id", entityColumn = "eventId")
    lateinit var sessions: List<SessionCacheEntity>
}