package com.oliva.antonio.data.network.entity.detail

import com.oliva.antonio.data.entity.event.EventEntity
import com.oliva.antonio.data.entity.session.SessionEntity

/**
 * Created by antonio on 1/20/18.
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
        SessionEntity(if (sessionApiDetail.name.isNullOrEmpty()) "" else sessionApiDetail.name!!,
                if (sessionApiDetail.champName.isNullOrEmpty()) "" else sessionApiDetail.champName!!,
                if (sessionApiDetail.startTime.isNullOrEmpty()) "" else sessionApiDetail.startTime!!,
                if (sessionApiDetail.endTime.isNullOrEmpty()) "" else sessionApiDetail.endTime!!)