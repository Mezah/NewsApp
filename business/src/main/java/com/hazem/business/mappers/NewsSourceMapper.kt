package com.hazem.business.mappers

import com.hazem.entities.headlines.local.Source
import com.hazem.entities.mappers.LocalMapper
import com.hazem.entities.sources.NewsSource

class NewsSourceMapper : LocalMapper<NewsSource, Source> {
    override fun convert(from: NewsSource): Source {
        return Source(from.id, from.name)
    }
}