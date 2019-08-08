package com.hazem.newslist.di

import com.hazem.business.di.BusinessModule
import com.hazem.newslist.viewmodels.NewsFilterViewModel
import com.hazem.newslist.viewmodels.NewsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.core.qualifier.StringQualifier
import org.koin.dsl.module

object NewsListModule : () -> Module {
    const val NEWS_LIST_VM = "News_List_ViewModel"
    const val FILTER_NEWS_VM = "Filter_News_List_VM"

    override fun invoke(): Module = module {

        viewModel(qualifier = StringQualifier(NEWS_LIST_VM)) {
            NewsListViewModel(get(StringQualifier(BusinessModule.TOP_HEADER)))
        }

        viewModel(qualifier = StringQualifier(FILTER_NEWS_VM)) {
            NewsFilterViewModel(
                get(StringQualifier(BusinessModule.NEWS_SOURCES))
                , get(StringQualifier(BusinessModule.COUNTRIES_CODES))
            )
        }
    }
}