package com.oliva.antonio.data.cache.room.event.session

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import com.oliva.antonio.data.cache.room.event.EventCacheEntity

/**
 * Created by antonio on 1/21/18.
 */

@Entity(tableName = "sessions",
        indices = [(Index("eventId"))],
        foreignKeys = [(ForeignKey(entity = EventCacheEntity::class,
                parentColumns = [("id")],
                childColumns = [("eventId")],
                onDelete = CASCADE))])
data class SessionCacheEntity(@ColumnInfo(name = "name") var name: String,
                              @ColumnInfo(name = "champName") var champName: String,
                              @ColumnInfo(name = "startTime") var startTime: String,
                              @ColumnInfo(name = "endTime") var endTime: String,
                              @ColumnInfo(name = "eventId") var eventId: Int,
                              @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
                              @ColumnInfo(name = "lastUpdate") var cacheLastUpdate: Long = System.currentTimeMillis())