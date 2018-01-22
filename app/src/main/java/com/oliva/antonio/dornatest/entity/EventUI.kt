package com.oliva.antonio.dornatest.entity

/**
 * Created by antonio on 1/22/18.
 */

data class EventUI(val id: Int,
                   val name: String,
                   val imageUrl: String,
                   val dateBegin: Long,
                   val dateFinish: Long,
                   val circuitFlag: String,
                   val sessions: List<SessionUI>)