package com.oliva.antonio.dornatest.di

import com.oliva.antonio.data.network.DornaService
import com.oliva.antonio.dornatest.network.LocalCacheInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by antonio on 12/3/17.
 */
@Module
class NetworkModule {

    /**
     * Provides network interceptors for okhttp client. In this case, just one interceptor is provided,
     * but is useful to do it that way for debug configurations (i.e.: loggin interceptor, stetho interceptor, etc)
     *
     * @param interceptor
     */
    @Provides
    @Singleton
    fun provideNetworkInterceptors(interceptor: LocalCacheInterceptor): ArrayList<Interceptor> {
        return arrayListOf(interceptor)
    }

    /**
     * Provides http client
     *
     * @param httpCacheDirectory Directory for cache responses
     * @param interceptors
     */
    @Provides
    @Singleton
    fun providesHttpClient(httpCacheDirectory: File, interceptors: ArrayList<Interceptor>): OkHttpClient {
        val cacheSize = 10L * 1024 * 1024 // 10 MiB
        val cache = Cache(httpCacheDirectory, cacheSize)

        val builder = OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .cache(cache)

        for (interceptor in interceptors) {
            builder.addNetworkInterceptor(interceptor)
        }

        return builder.build()
    }

    /**
     * Provides gnome api service
     *
     * @param httpClient
     */
    @Provides
    @Singleton
    fun providesDornaService(httpClient: OkHttpClient): DornaService {
        val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .baseUrl("http://staging.motogpofficialapp.motogp.com/")
                .build()

        return retrofit.create(DornaService::class.java)
    }
}