package com.oliva.antonio.data.cache.room.event

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Created by antonio on 12/3/17.
 */

@Entity(tableName = "events")
data class EventCacheEntity(@PrimaryKey @ColumnInfo(name = "id") val id: Int,
                            @ColumnInfo(name = "name") var name: String,
                            @ColumnInfo(name = "imageUrl") var imageUrl: String,
                            @ColumnInfo(name = "dateBegin") var dateBegin: String,
                            @ColumnInfo(name = "dateFinish") var dateFinish: String,
                            @ColumnInfo(name = "circuitFlag") var circuitFlag: String,
                            @ColumnInfo(name = "lastUpdate") var cacheLastUpdate: Long = System.currentTimeMillis())
