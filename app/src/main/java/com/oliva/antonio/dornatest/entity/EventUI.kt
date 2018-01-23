package com.oliva.antonio.dornatest.entity

/**
 * Created by antonio
 */

data class EventUI(val id: Int,
                   val name: String,
                   val imageUrl: String,
                   val dateBegin: Long,
                   val dateFinish: Long,
                   val circuitFlag: String,
                   val sessions: List<SessionUI>)