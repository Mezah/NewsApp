package com.hazem.network.remote

import com.hazem.entities.headlines.remote.NewsHeadersResponse
import com.hazem.entities.sources.NewsSourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsEndPoints {
    @GET("top-headlines")
    suspend fun loadTopHeadLines(
        @Query("language") language: String
    ): NewsHeadersResponse

    @GET("sources")
    suspend fun loadNewsSources(): NewsSourcesResponse
}