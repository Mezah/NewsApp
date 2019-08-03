package com.hazem.entities

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class ApiError(val errorMessage: String) : Result<Nothing>()
    data class Error(val exception: Exception) : Result<Nothing>()
    object Loading : Result<Nothing>()
}