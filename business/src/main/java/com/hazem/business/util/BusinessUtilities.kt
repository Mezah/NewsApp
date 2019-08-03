package com.hazem.business.util

import java.text.SimpleDateFormat
import java.util.*

fun headLinesDateFormatter(dateFromApi: String?): String {
    val inputDateTemplate = "yyyy-MM-dd'T'HH:mm:ss"
    val outputDateTemplate = "d MMM yy"
    val inputSimpleDateFormat = SimpleDateFormat(inputDateTemplate, Locale.getDefault())
    val apiDate = inputSimpleDateFormat.parse(dateFromApi)
    val outputSimpleDateFormat = SimpleDateFormat(outputDateTemplate, Locale.getDefault())
    return outputSimpleDateFormat.format(apiDate)
}