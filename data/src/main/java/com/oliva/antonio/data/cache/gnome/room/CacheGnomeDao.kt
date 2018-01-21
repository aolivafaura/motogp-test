package com.oliva.antonio.data.cache.gnome.room

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import io.reactivex.Flowable

/**
 * Created by antonio on 12/3/17.
 */
@Dao
interface CacheGnomeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gnomeEntities: List<GnomeRoomEntity>): List<Long>

    @Query("SELECT * FROM gnomes LIMIT :limit OFFSET :offset")
    fun getAll(offset: Int, limit: Int): Flowable<List<GnomeRoomEntity>>

    @Query("SELECT * FROM gnomes WHERE name LIKE :filter LIMIT :limit OFFSET :offset")
    fun getFilteredByName(offset: Int, filter: String, limit: Int): Flowable<List<GnomeRoomEntity>>

    @Query("SELECT * FROM gnomes WHERE id = :id")
    fun get(id: Int): Flowable<GnomeRoomEntity>

    @Query("SELECT * FROM gnomes WHERE name IN (:values)")
    fun getWithName(values: List<String>): Flowable<List<GnomeRoomEntity>>
}
