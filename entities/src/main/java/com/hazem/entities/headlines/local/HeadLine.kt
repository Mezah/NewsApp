package com.hazem.entities.headlines.local

import java.io.Serializable


/**
 * The required elements from API response that will be viewed.
 */
data class HeadLine(
    val title: String?,
    val date: String?,
    val logo: String?,
    val description: String?,
    val url: String?,
    val author: String?,
    val source:String?

) : Serializable
