package com.hazem.entities.clients

import com.hazem.entities.Languages
import com.hazem.entities.headlines.remote.NewsHeadersResponse
import com.hazem.entities.sources.NewsSourcesResponse

interface INewsWebClient {

    suspend fun loadLatestNewsHeaders(lang: Languages): NewsHeadersResponse

    suspend fun loadNewsSources(): NewsSourcesResponse
}