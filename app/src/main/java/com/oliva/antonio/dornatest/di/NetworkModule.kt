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
     * Provides http client
     */
    @Provides
    @Singleton
    fun providesHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)

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