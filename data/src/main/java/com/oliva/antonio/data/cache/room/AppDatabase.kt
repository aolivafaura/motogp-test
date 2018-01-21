package com.oliva.antonio.data.cache.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.oliva.antonio.data.cache.room.event.EventCacheDao
import com.oliva.antonio.data.cache.room.event.EventCacheEntity
import com.oliva.antonio.data.cache.room.event.session.SessionCacheEntity

/**
 * Created by antonio on 12/3/17.
 */
@Database(entities = [(EventCacheEntity::class), (SessionCacheEntity::class)],
        version = 1,
        exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun eventCacheDao(): EventCacheDao
}
