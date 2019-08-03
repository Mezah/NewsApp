package com.hazem.business.mappers

import com.hazem.business.util.headLinesDateFormatter
import com.hazem.entities.headlines.local.HeadLine
import com.hazem.entities.headlines.remote.Article
import com.hazem.entities.mappers.LocalMapper

class NewsHeadersMapper:LocalMapper<Article,HeadLine> {

    override fun convert(from: Article): HeadLine {
        val date = headLinesDateFormatter(from.publishedAt)
        return HeadLine(from.title,date,from.urlToImage)
    }
}