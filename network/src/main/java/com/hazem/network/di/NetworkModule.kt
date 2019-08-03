package com.hazem.network.di

import org.koin.core.module.Module
import org.koin.dsl.module

object NetworkModule : (String, String) -> Module {
    override fun invoke(baseUrl: String, apiKey: String): Module = module {

    }
}