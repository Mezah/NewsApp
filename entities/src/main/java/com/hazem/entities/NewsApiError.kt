package com.hazem.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NewsApiError(

	@SerializedName("code")
	@Expose
	val code: String? = null,

	@SerializedName("message")
	@Expose
	val message: String? = null,

	@SerializedName("status")
	@Expose
	val status: String? = null
)