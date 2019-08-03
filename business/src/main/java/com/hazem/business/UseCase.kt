package com.hazem.business

import com.google.gson.Gson
import com.hazem.business.di.BusinessModule
import com.hazem.entities.NewsApiError
import com.hazem.entities.Result
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.qualifier.StringQualifier
import retrofit2.HttpException

abstract class UseCase<in P, R> : KoinComponent {

    private val gson: Gson by inject(StringQualifier(BusinessModule.GSON))

    suspend operator fun invoke(parameters: P): Result<R> {
        return try {
            Result.Success(execute(parameters))
        } catch (e: Exception) {
            if (e is HttpException) {
                val errorResponse = e.response()
                val errorBody = errorResponse?.errorBody()
                val newsApiError = gson.fromJson(errorBody?.string(), NewsApiError::class.java)
                Result.ApiError(newsApiError.message ?: "Something go Wrong")
            } else {
                Result.Error(e)
            }
        }
    }

    protected abstract suspend fun execute(parameters: P): R
}