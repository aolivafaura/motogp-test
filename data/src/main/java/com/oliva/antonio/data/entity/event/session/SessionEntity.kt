package com.oliva.antonio.data.entity.event.session

/**
 * Created by antonio on 1/20/18.
 */

data class SessionEntity(val id: Int,
                         val name: String,
                         val champName: String,
                         val startTime: String,
                         val endTime: String,
                         val cacheLastUpdate: Long = 0)