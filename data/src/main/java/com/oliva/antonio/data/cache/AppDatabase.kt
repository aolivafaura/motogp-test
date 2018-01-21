package com.oliva.antonio.data.repository.gnome

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.oliva.antonio.data.cache.gnome.room.CacheGnomeDao
import com.oliva.antonio.data.cache.gnome.room.GnomeRoomEntity

/**
 * Created by antonio on 12/3/17.
 */
@Database(entities = [(GnomeRoomEntity::class)], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gnomeDao(): CacheGnomeDao
}
