package com.oliva.antonio.dornatest.di

import com.oliva.antonio.data.network.DornaService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by antonio
 */
@Module
class NetworkModule {

    /**
     * Provides network interceptors for okhttp client. In this case, no interceptor is provided,
     * but is useful to do it that way for debug configurations (i.e.: loggin interceptor, stetho interceptor, etc)
     *
     */
    @Provides
    @Singleton
    fun provideNetworkInterceptors(): ArrayList<Interceptor> {
        return ArrayList()
    }

    /**
     * Provides http client
     *
     * @param interceptors
     */
    @Provides
    @Singleton
    fun providesHttpClient(interceptors: ArrayList<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)

        for (interceptor in interceptors) {
            builder.addNetworkInterceptor(interceptor)
        }

        return builder.build()
    }

    /**
     * Provides api service
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