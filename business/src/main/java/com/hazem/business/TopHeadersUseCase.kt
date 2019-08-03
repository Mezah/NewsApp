package com.hazem.business

import com.hazem.entities.clients.INewsWebClient
import com.hazem.entities.headlines.local.HeadLine
import com.hazem.entities.headlines.remote.Article
import com.hazem.entities.mappers.LocalMapper
import org.koin.core.inject
import java.util.*

class TopHeadersUseCase : UseCase<WeakHashMap<String, String>, List<HeadLine>>() {

    private val webClient: INewsWebClient by inject<INewsWebClient>()
    private val headLineMapper: LocalMapper<Article, HeadLine> by inject()

    override suspend fun execute(parameters: WeakHashMap<String, String>): List<HeadLine> {
        val language = parameters["Language"] ?: "en"
//        val source = parameters["Source"]
        return webClient.loadLatestNewsHeaders(language).articles?.map { headLineMapper.convert(it) } ?: emptyList()
    }
}