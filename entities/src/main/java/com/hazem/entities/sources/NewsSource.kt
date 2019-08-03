package com.hazem.entities.sources

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsSource(

	@SerializedName("country")
	@Expose
	val country: String? = null,

	@SerializedName("name")
	@Expose
	val name: String? = null,

	@SerializedName("description")
	@Expose
	val description: String? = null,

	@SerializedName("language")
	@Expose
	val language: String? = null,

	@SerializedName("id")
	@Expose
	val id: String? = null,

	@SerializedName("category")
	@Expose
	val category: String? = null,

	@SerializedName("url")
	@Expose
	val url: String? = null
)