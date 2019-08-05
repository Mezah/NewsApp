package com.hazem.business

import com.hazem.business.di.BusinessModule
import com.hazem.entities.clients.INewsWebClient
import com.hazem.entities.headlines.local.HeadLine
import com.hazem.entities.headlines.remote.Article
import com.hazem.entities.mappers.LocalMapper
import com.hazem.network.di.NetworkModule
import org.koin.core.inject
import org.koin.core.qualifier.StringQualifier
import java.util.*

class TopHeadersUseCase : UseCase<WeakHashMap<String, String>, List<HeadLine>>() {

    private val webClient: INewsWebClient by inject(StringQualifier(NetworkModule.NEWS_WEB_CLIENT))
    private val headLineMapper: LocalMapper<Article, HeadLine> by inject(StringQualifier(BusinessModule.HEAD_LINE_MAPPER))

    override suspend fun execute(parameters: WeakHashMap<String, String>): List<HeadLine> {
        val language = parameters["Language"] ?: "en"
//        val source = parameters["Source"]
        return webClient.loadLatestNewsHeaders(language).articles?.map { headLineMapper.convert(it) } ?: emptyList()
    }
}