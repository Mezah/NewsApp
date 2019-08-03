package com.hazem.entities.headlines.remote

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source(

	@SerializedName("name")
	@Expose
	val name: String? = null,

	@SerializedName("id")
	@Expose
	val id: String? = null
)