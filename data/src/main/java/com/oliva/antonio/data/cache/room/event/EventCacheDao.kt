package com.oliva.antonio.data.cache.room.event

import android.arch.persistence.room.*
import com.oliva.antonio.data.cache.room.event.session.SessionCacheEntity
import io.reactivex.Flowable

/**
 * Created by antonio
 */
@Dao
interface EventCacheDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertEvents(eventEntities: List<EventCacheEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSessions(sessionEntities: List<SessionCacheEntity>): List<Long>

    @Query("SELECT * FROM events")
    fun getAll(): Flowable<List<EventCacheEntity>>

    @Transaction
    @Query("SELECT * FROM events WHERE id = :id")
    fun get(id: Int): Flowable<EventCacheWithSessions>
}
