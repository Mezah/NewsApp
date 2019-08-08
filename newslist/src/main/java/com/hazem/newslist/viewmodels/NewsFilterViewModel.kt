package com.hazem.newslist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazem.business.CountriesCodesUseCase
import com.hazem.business.NewsSourcesUseCase
import com.hazem.entities.Result
import com.hazem.entities.headlines.local.Source
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsFilterViewModel(
    private val sourcesUseCase: NewsSourcesUseCase,
    private val countriesCodesUseCase: CountriesCodesUseCase
) : ViewModel() {

    private val mCountriesCodesList: MutableLiveData<MutableList<String>> = MutableLiveData()
    val countriesCodesList: LiveData<MutableList<String>> = mCountriesCodesList

    private val mNewsSources: MutableLiveData<MutableList<Source>> = MutableLiveData()
    val newsSources: LiveData<MutableList<Source>> = mNewsSources

    private val mErrorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = mErrorMessage


    fun loadNewsSources() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                sourcesUseCase(Unit)
            }
            when (result) {
                is Result.Success -> {
                    mNewsSources.postValue(result.data
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

}