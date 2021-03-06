package com.oliva.antonio.dornatest.di

import android.arch.persistence.room.Room
import android.content.Context
import com.oliva.antonio.common.network.Connectivity
import com.oliva.antonio.data.cache.CacheDao
import com.oliva.antonio.data.cache.room.AppDatabase
import com.oliva.antonio.data.cache.room.EventCacheDaoWrapper
import com.oliva.antonio.data.entity.event.EventEntity
import com.oliva.antonio.data.network.DornaService
import com.oliva.antonio.data.repository.EventDataRepository
import com.oliva.antonio.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by antonio
 */

@Module
class RepositoryModule {

    /**
     * Provides local cache data access object
     *
     */
    @Provides
    @Singleton
    fun provideEventCacheDao(context: Context): CacheDao<EventEntity> {
        val appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "dorna.db")
                .fallbackToDestructiveMigration().build()
        return EventCacheDaoWrapper(appDatabase.eventCacheDao())
    }

    /**
     * Provides events repository
     *
     * @param cacheDao
     */
    @Provides
    @Singleton
    fun provideEventRepository(cacheDao: CacheDao<EventEntity>,
                               dornaService: DornaService,
                               connectivity: Connectivity): EventRepository =
            EventDataRepository(cacheDao, dornaService, connectivity)
}
