package com.oliva.antonio.dornatest.entity

import com.oliva.antonio.domain.entity.Event
import com.oliva.antonio.domain.entity.Session
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by antonio
 */

fun mapEventToEventUI(event: Event): EventUI {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    var date1 = format.parse(event.dateBegin)
    val timestamp = date1.time
    val date2 = format.parse(event.dateFinish)
    val timestamp2 = date2.time

    return EventUI(event.id,
            event.name,
            event.imageUrl,
            timestamp,
            timestamp2,
            event.circuitFlag,
            event.sessions.map { mapSessionToSessionUI(it) })
}

fun mapSessionToSessionUI(session: Session): SessionUI {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    var date = format.parse(session.startTime)
    val timestamp = date.time
    val date2 = format.parse(session.endTime)
    val timestamp2 = date2.time

    return SessionUI(session.name,
            session.champName,
            timestamp,
            timestamp2)
}