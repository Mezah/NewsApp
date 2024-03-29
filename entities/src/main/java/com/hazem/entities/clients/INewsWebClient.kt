package com.hazem.entities.clients

import com.hazem.entities.headlines.remote.NewsHeadersResponse
import com.hazem.entities.sources.NewsSourcesResponse

interface INewsWebClient {

    suspend fun loadLatestNewsHeaders(lang: String,country:String?,sourc:String?): NewsHeadersResponse

    suspend fun loadNewsSources(): NewsSourcesResponse
}