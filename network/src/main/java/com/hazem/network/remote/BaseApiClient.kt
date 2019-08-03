package com.hazem.network.remote

import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

internal object BaseApiClient {
    fun createOkHttpClient(interceptors: Array<Interceptor>): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
        for (interceptor in interceptors)
            builder.addInterceptor(interceptor)
        return builder.build()
    }

    fun createRetroFitBuilder(baserUrl:String,client: OkHttpClient): Retrofit.Builder {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.excludeFieldsWithoutExposeAnnotation()
        val gson = gsonBuilder
            .create()
        val builder = Retrofit.Builder()
        builder.baseUrl(baserUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
        return builder
    }

}