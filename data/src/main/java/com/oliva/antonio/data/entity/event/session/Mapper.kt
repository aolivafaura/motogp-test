package com.oliva.antonio.data.entity.event.session

import com.oliva.antonio.domain.entity.Session

/**
 * Created by antonio
 */

internal fun mapSessionEntityToSession(sessionEntity: SessionEntity) = Session(
        sessionEntity.name,
        sessionEntity.champName,
        sessionEntity.startTime,
        sessionEntity.endTime)