package com.oliva.antonio.data.entity.event

import com.oliva.antonio.data.entity.session.SessionEntity

/**
 * Created by antonio on 11/30/17.
 */

internal data class EventEntity(val id: Int,
                                val name: String,
                                val imageUrl: String,
                                val dateBegin: String,
                                val dateFinish: String,
                                val circuitFlag: String,
                                val sessions: List<SessionEntity>)