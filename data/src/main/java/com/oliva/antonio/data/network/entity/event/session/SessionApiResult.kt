package com.oliva.antonio.data.network.entity.event.session

import com.google.gson.annotations.SerializedName

/**
 * Created by antonio on 1/20/18.
 */

data class DetailApiResult(@SerializedName("id") val id: Int,
                           @SerializedName("name") val name: String?,
                           @SerializedName("top_mobile_image_url") val imageUrl: String?,
                           @SerializedName("date_begin") val dateBegin: String?,
                           @SerializedName("date_finish") val dateFinish: String?,
                           @SerializedName("circuit_flag") val circuitFlag: String?,
                           @SerializedName("sessions") val sessionData: SessionsDataObject?)

data class SessionsDataObject(@SerializedName("data") val data: List<SessionApiDetail>?)

data class SessionApiDetail(@SerializedName("start_time") val startTime: String?,
                            @SerializedName("end_time") val endTime: String?,
                            @SerializedName("champ_name") val champName: String?,
                            @SerializedName("name") val name: String?)