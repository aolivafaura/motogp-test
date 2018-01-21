package com.oliva.antonio.dornatest.network

import com.oliva.antonio.common.network.Connectivity
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

/**
 * This class is used as first level cache for network responses.
 * Is allowed a 120 seconds cache for network responses.
 * If there is no connectivity, 1 day stale is allowed.
 *
 * Created by antonio on 12/3/17.
 */
const val CACHE_CONTROL_MAX_AGE = 0
const val CACHE_CONTROL_MAX_STALE = 365
const val CACHE_MAX_AGE = 120
const val CACHE_MAX_STALE = 60 * 60 * 24 * 1 // 1 day stale

class LocalCacheInterceptor(val connectivity: Connectivity) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        val cacheBuilder = CacheControl.Builder()
        cacheBuilder.maxAge(CACHE_CONTROL_MAX_AGE, TimeUnit.SECONDS)
        cacheBuilder.maxStale(CACHE_CONTROL_MAX_STALE, TimeUnit.DAYS)

        val cacheControl = cacheBuilder.build()

        var request = chain!!.request()
        if (connectivity.isConnected()) {
            request = request.newBuilder().cacheControl(cacheControl).build()
        }

        val response = chain.proceed(request)
        // Check connectivity
        return if (connectivity.isConnected()) {
            val maxAge = CACHE_MAX_AGE
            response.newBuilder().header("Cache-Control",
                    "public, max-age=" + maxAge).build()
        }
        // If there is no network connection, return cached response
        else {
            val maxStale = CACHE_MAX_STALE
            response.newBuilder().header("Cache-Control",
                    "public, only-if-cached, max-stale=" + maxStale).build()
        }
    }
}
