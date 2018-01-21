package com.oliva.antonio.dornatest.di

import android.content.Context
import com.oliva.antonio.common.network.Connectivity
import com.oliva.antonio.dornatest.DornaApp
import com.oliva.antonio.dornatest.network.LocalCacheInterceptor
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by antonio on 12/3/17.
 */
@Module
class AppModule {

    /**
     * Provides application context
     * @param app
     */
    @Provides
    @Singleton
    fun provideContext(app: DornaApp): Context = app.applicationContext!!

    /**
     * Provides ui thread scheduler
     */
    @Provides
    @Named("uiThread")
    fun provideUIThread(): Scheduler = AndroidSchedulers.mainThread()

    /**
     * Provides background thread scheduler
     */
    @Provides
    @Named("workerThread")
    fun provideWorkerThread(): Scheduler = Schedulers.io()

    /**
     * Provides connectivity manager class
     * @param context
     */
    @Provides
    @Singleton
    fun provideConnectivityManager(context: Context): Connectivity = Connectivity(context)

    /**
     * Provides cache directory for network responses
     * @param context
     */
    @Provides
    @Singleton
    fun provideCacheDirectory(context: Context): File = File(context.cacheDir, "responses")

    /**
     * Provides network interceptor to cache responses in memory
     * @param connectivity Connectivity manager
     */
    @Provides
    @Singleton
    fun provideCacheInterceptor(connectivity: Connectivity): LocalCacheInterceptor = LocalCacheInterceptor(connectivity)
}
