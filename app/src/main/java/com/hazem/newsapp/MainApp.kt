package com.hazem.newsapp

import android.app.Application
import com.hazem.business.di.BusinessModule
import com.hazem.network.di.NetworkModule
import com.hazem.newslist.di.NewsListModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MainApp)
            modules(
                listOf(
                    NetworkModule(BuildConfig.BASE_URL, BuildConfig.API_KEY),
                    BusinessModule(),
                    NewsListModule()
                )
            )


        }
    }
}