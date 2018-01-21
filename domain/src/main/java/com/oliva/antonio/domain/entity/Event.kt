package com.oliva.antonio.domain.entity

/**
 * Created by antonio on 12/3/17.
 */
data class Event(val id: Int,
                 val name: String,
                 val imageUrl: String,
                 val dateBegin: String,
                 val dateFinish: String,
                 val circuitFlag: String,
                 val sessions: List<Session>)
