package com.hazem.business.mappers

import com.hazem.business.util.headLinesDateFormatter
import com.hazem.entities.headlines.local.HeadLine
import com.hazem.entities.headlines.remote.Article
import com.hazem.entities.mappers.LocalMapper

/**
 * Mapping only the required parameters from API response.
 * This will prevent UI from any change in api response by not to expose
 * the response directly.
 */
class NewsHeadersMapper : LocalMapper<Article, HeadLine> {

    override fun convert(from: Article): HeadLine {
        val date = headLinesDateFormatter(from.publishedAt)
        return HeadLine(from.title, date, from.urlToImage, from.content, from.url, from.author, from.source?.name)
    }
}