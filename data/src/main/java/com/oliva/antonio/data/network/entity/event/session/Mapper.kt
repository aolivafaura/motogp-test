package com.oliva.antonio.data.network.entity.event.session

import com.oliva.antonio.data.entity.event.EventEntity
import com.oliva.antonio.data.entity.event.session.SessionEntity

/**
 * Created by antonio
 */

internal fun mapDetailApiResultToEventEntity(detailApiResult: DetailApiResult): EventEntity =
        EventEntity(detailApiResult.id,
                if (detailApiResult.name.isNullOrEmpty()) "" else detailApiResult.name!!,
                if (detailApiResult.imageUrl.isNullOrEmpty()) "" else detailApiResult.imageUrl!!,
                if (detailApiResult.dateBegin.isNullOrEmpty()) "" else detailApiResult.dateBegin!!,
                if (detailApiResult.dateFinish.isNullOrEmpty()) "" else detailApiResult.dateFinish!!,
                if (detailApiResult.circuitFlag.isNullOrEmpty()) "" else detailApiResult.circuitFlag!!,
                if (detailApiResult.sessionData?.data?.isNotEmpty() == true)
                    mapSessionDataObjectToSessionDetailEntity(detailApiResult.sessionData)
                else emptyList())

private fun mapSessionDataObjectToSessionDetailEntity(sessionsDataObject: SessionsDataObject): List<SessionEntity> =
        sessionsDataObject.data!!.map { mapSessionApiDetailToSessionEntity(it) }

private fun mapSessionApiDetailToSessionEntity(sessionApiDetail: SessionApiDetail): SessionEntity =
        SessionEntity(sessionApiDetail.id,
                if (sessionApiDetail.name.isNullOrEmpty()) "" else sessionApiDetail.name!!,
                if (sessionApiDetail.champName.isNullOrEmpty()) "" else sessionApiDetail.champName!!,
                if (sessionApiDetail.startTime.isNullOrEmpty()) "" else sessionApiDetail.startTime!!,
                if (sessionApiDetail.endTime.isNullOrEmpty()) "" else sessionApiDetail.endTime!!)