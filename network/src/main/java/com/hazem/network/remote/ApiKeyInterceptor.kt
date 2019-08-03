package com.hazem.network.remote

import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor(private val apiKey: String) : Interceptor {
    companion object {
        const val API_HEADER_KEY: String = "X-Api-Key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()
        val newRequest = oldRequest.newBuilder()
            .addHeader(API_HEADER_KEY, apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}