package com.oliva.antonio.data.network.entity.event

import com.google.gson.annotations.SerializedName

/**
 * Created by antonio on 12/3/17.
 */
data class EventsApiResult(@SerializedName("events") val events: List<EventApiEntity>)

data class EventApiEntity(@SerializedName("id") val id: Int,
                          @SerializedName("name") val name: String?,
                          @SerializedName("top_mobile_image_url") val imageUrl: String?,
                          @SerializedName("date_begin") val dateBegin: String?,
                          @SerializedName("date_finish") val dateFinish: String?,
                          @SerializedName("circuit_flag") val circuitFlag: String?)