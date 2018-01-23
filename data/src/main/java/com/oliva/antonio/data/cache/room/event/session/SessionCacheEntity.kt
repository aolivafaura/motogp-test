package com.oliva.antonio.data.cache.room.event.session

import android.arch.persistence.room.*
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.ForeignKey.NO_ACTION
import com.oliva.antonio.data.cache.room.event.EventCacheEntity

/**
 * Created by antonio on 1/21/18.
 */

@Entity(tableName = "sessions",
        indices = [(Index("eventId")), (Index("eventId", "sessionId", unique = true))],
        foreignKeys = [(ForeignKey(entity = EventCacheEntity::class,
                parentColumns = [("id")],
                childColumns = [("eventId")],
                onDelete = NO_ACTION))])
data class SessionCacheEntity(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Int = 0,
                              @ColumnInfo(name = "sessionId") var sessionId: Int,
                              @ColumnInfo(name = "name") var name: String,
                              @ColumnInfo(name = "champName") var champName: String,
                              @ColumnInfo(name = "startTime") var startTime: String,
                              @ColumnInfo(name = "endTime") var endTime: String,
                              @ColumnInfo(name = "eventId") var eventId: Int,
                              @ColumnInfo(name = "lastUpdate") var cacheLastUpdate: Long = System.currentTimeMillis())