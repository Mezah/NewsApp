package com.hazem.network.di

import com.hazem.entities.clients.INewsWebClient
import com.hazem.network.remote.ApiKeyInterceptor
import com.hazem.network.remote.createWebClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module

object NetworkModule : (String, String) -> Module {
    private const val API_INTERCEPTOR = "ApiInterceptor"
    private const val LOGGING_INTERCEPTOR = "LoggingInterceptor"
    const val NEWS_WEB_CLIENT = "NewsWebClient"


    override fun invoke(baseUrl: String, apiKey: String): Module = module {

        single(StringQualifier(API_INTERCEPTOR)) {
            ApiKeyInterceptor(apiKey)
        }
        single(StringQualifier(LOGGING_INTERCEPTOR)) {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
        single(createdAtStart = true, qualifier = StringQualifier(NEWS_WEB_CLIENT)) {
            createWebClient(
                baseUrl,
                arrayOf(
                    get<ApiKeyInterceptor>(StringQualifier(API_INTERCEPTOR)),
                    get<HttpLoggingInterceptor>(StringQualifier(LOGGING_INTERCEPTOR))
                )
            ).value
        } bind INewsWebClient::class
    }
}