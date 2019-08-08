package com.hazem.business

import com.hazem.business.di.BusinessModule
import com.hazem.entities.clients.INewsWebClient
import com.hazem.entities.headlines.local.Source
import com.hazem.entities.mappers.LocalMapper
import com.hazem.entities.sources.NewsSource
import com.hazem.network.di.NetworkModule
import org.koin.core.inject
import org.koin.core.qualifier.StringQualifier

class NewsSourcesUseCase : UseCase<Unit, List<Source>>() {
    private val webClient: INewsWebClient by inject(StringQualifier(NetworkModule.NEWS_WEB_CLIENT))
    private val sourceMapper: LocalMapper<NewsSource, Source> by inject(StringQualifier(BusinessModule.NEWS_SOURCE_MAPPER))

    override suspend fun execute(parameters: Unit): List<Source> {
        return webClient.loadNewsSources()
            .sources
            ?.map { sourceMapper.convert(it) }
            ?: emptyList()
    }
}