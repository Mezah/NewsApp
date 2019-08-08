package com.hazem.business.di

import com.google.gson.Gson
import com.hazem.business.CountriesCodesUseCase
import com.hazem.business.NewsSourcesUseCase
import com.hazem.business.TopHeadersUseCase
import com.hazem.business.mappers.NewsHeadersMapper
import com.hazem.business.mappers.NewsSourceMapper
import com.hazem.entities.mappers.LocalMapper
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.bind
import org.koin.dsl.module

object BusinessModule : () -> Module {
    const val HEAD_LINE_MAPPER = "Head_Line_Mapper"
    const val NEWS_SOURCE_MAPPER = "News_Sources_Mapper"
    const val GSON = "Gson"
    const val TOP_HEADER = "Top_Headers"
    const val COUNTRIES_CODES = "Countries_Code"
    const val NEWS_SOURCES = "News_Sources"

    override fun invoke(): Module = module {

        single(StringQualifier(GSON)) {
            Gson()
        }
        factory(StringQualifier(HEAD_LINE_MAPPER)) {
            NewsHeadersMapper()
        } bind LocalMapper::class

        factory(StringQualifier(NEWS_SOURCE_MAPPER)) {
            NewsSourceMapper()
        } bind LocalMapper::class

        factory(StringQualifier(TOP_HEADER)) {
            TopHeadersUseCase()
        }

        factory(StringQualifier(COUNTRIES_CODES)) {
            CountriesCodesUseCase()
        }

        factory(StringQualifier(NEWS_SOURCES)) {
            NewsSourcesUseCase()
        }
    }
}