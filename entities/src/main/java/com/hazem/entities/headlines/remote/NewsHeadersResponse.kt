package com.hazem.entities.headlines.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsHeadersResponse(

    @SerializedName("totalResults")
    @Expose
    val totalResults: Int? = null,

    @SerializedName("articles")
    @Expose
    val articles: List<Article>? = null,

    @SerializedName("status")
    @Expose
    val status: String? = null
)