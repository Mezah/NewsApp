package com.hazem.entities.sources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsSourcesResponse(

	@SerializedName("sources")
    @Expose
	val sources: List<NewsSource?>? = null,

	@SerializedName("status")
    @Expose
	val status: String? = null
)