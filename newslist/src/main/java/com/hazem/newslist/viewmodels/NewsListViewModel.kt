package com.hazem.newslist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazem.business.CountriesCodesUseCase
import com.hazem.business.NewsSourcesUseCase
import com.hazem.business.TopHeadersUseCase
import com.hazem.entities.Languages
import com.hazem.entities.Result
import com.hazem.entities.headlines.local.HeadLine
import com.hazem.entities.headlines.local.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.set

class NewsListViewModel(
    private val topHeadersUseCase: TopHeadersUseCase,
    private val sourcesUseCase: NewsSourcesUseCase,
    private val countriesCodesUseCase: CountriesCodesUseCase
) : ViewModel() {

    private var mNewsList: MutableLiveData<List<HeadLine>> = MutableLiveData()
    val newsList: LiveData<List<HeadLine>> = mNewsList

    private var mErrorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = mErrorMessage

    private val mCountriesCodesList: MutableLiveData<MutableList<String>> = MutableLiveData()
    val countriesCodesList: LiveData<MutableList<String>> = mCountriesCodesList

    private val mNewsSources: MutableLiveData<MutableList<Source>> = MutableLiveData()
    val newsSources: LiveData<MutableList<Source>> = mNewsSources

    fun loadNewsList(country: String? = null, source: String? = null) {
        val headLineParameters = WeakHashMap<String, String>()
        headLineParameters["Language"] = Languages.EN.langName
        headLineParameters["Country"] = country
        headLineParameters["Source"] = source
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                topHeadersUseCase(headLineParameters)
            }
            when (result) {
                is Result.Success -> {
                    mNewsList.postValue(result.data)
                }
                is Result.ApiError -> {
                    mErrorMessage.postValue(result.errorMessage)
                }
                else -> {
                    //Log the error
                }
            }
        }

    }

    fun loadNewsSources() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                sourcesUseCase(Unit)
            }
            when (result) {
                is Result.Success -> {
                    mNewsSources.postValue(
                        result.data
                            .toMutableList()
                    )
                }
                is Result.ApiError -> {
                    mErrorMessage.postValue(result.errorMessage)
                }
                else -> {
                    // log Error
                }
            }
        }
    }

    fun loadCountriesCodes() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                countriesCodesUseCase(Unit)
            }
            when (result) {
                is Result.Success -> {
                    mCountriesCodesList.postValue(result.data.toMutableList())
                }
                is Result.ApiError -> {
                    mErrorMessage.postValue(result.errorMessage)
                }
                else -> {
                    // log Error
                }
            }
        }
    }

    fun setCountryCode(countryCode: String) {
        loadNewsList(countryCode)
    }

    fun setNewsSource(source: String) {
        loadNewsList(source)
    }
}