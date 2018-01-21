package com.oliva.antonio.brastlewarkguide.di

import android.arch.persistence.room.Room
import android.content.Context
import com.oliva.antonio.data.cache.CacheDao
import com.oliva.antonio.data.cache.gnome.room.CacheGnomeDaoWrapper
import com.oliva.antonio.data.entity.GnomeEntity
import com.oliva.antonio.data.network.DornaService
import com.oliva.antonio.data.repository.gnome.AppDatabase
import com.oliva.antonio.data.repository.gnome.EventDataRepository
import com.oliva.antonio.domain.repository.EventRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by antonio on 12/3/17.
 */

@Module
class RepositoryModule {

    /**
     * Provides local cache data access object
     *
     * @param appDatabase
     */
    @Provides
    @Singleton
    fun provideCacheGnomeDao(context: Context): CacheDao<GnomeEntity> {
        val appDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "dorna.db")
                .fallbackToDestructiveMigration().build()
        return CacheGnomeDaoWrapper(appDatabase.gnomeDao())
    }

    /**
     * Pagination
     */
    @Provides
    @Singleton
    fun providePaginationLimit(): Int = 50

    /**
     * Provides gnomes repository
     *
     * @param cacheDao
     */
    @Provides
    @Singleton
    fun provideGnomeRepository(cacheDao: CacheDao<GnomeEntity>, dornaService: DornaService, paginationLimit: Int): EventRepository =
            EventDataRepository(cacheDao, dornaService, paginationLimit)
}
