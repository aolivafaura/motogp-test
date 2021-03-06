package com.oliva.antonio.dornatest.di

import android.content.Context
import com.oliva.antonio.common.network.Connectivity
import com.oliva.antonio.dornatest.DornaApp
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by antonio
 */
@Module
class AppModule {

    /**
     * Provides application context
     * @param app
     */
    @Provides
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
}
