package com.hazem.network.remote

import com.hazem.entities.clients.INewsWebClient
import com.hazem.entities.headlines.remote.NewsHeadersResponse
import com.hazem.entities.sources.NewsSourcesResponse
import okhttp3.Interceptor
import retrofit2.Retrofit


fun createWebClient(
    baseUrl: String,
    interceptors: Array<Interceptor>
): Lazy<INewsWebClient> = lazy {
    val okHttpClient = BaseApiClient.createOkHttpClient(interceptors)
    val retrofitBuilder = BaseApiClient.createRetroFitBuilder(baseUrl, okHttpClient)
    NewsWebClient(retrofitBuilder)
}

internal class NewsWebClient(private var builder: Retrofit.Builder) : INewsWebClient {

    private val apiEndPoints: NewsEndPoints by lazy { builder.build().create(NewsEndPoints::class.java) }

    override suspend fun loadLatestNewsHeaders(lang:String): NewsHeadersResponse {
        return apiEndPoints.loadTopHeadLines(lang)
    }

    override suspend fun loadNewsSources(): NewsSourcesResponse {
        return apiEndPoints.loadNewsSources()
    }
}
