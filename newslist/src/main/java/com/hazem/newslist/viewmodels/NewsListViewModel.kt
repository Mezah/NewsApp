package com.hazem.newslist.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hazem.business.TopHeadersUseCase
import com.hazem.entities.Languages
import com.hazem.entities.Result
import com.hazem.entities.headlines.local.HeadLine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.collections.set

class NewsListViewModel(private val topHeadersUseCase: TopHeadersUseCase) : ViewModel() {


    private var mNewsList: MutableLiveData<List<HeadLine>> = MutableLiveData()
    val newsList:LiveData<List<HeadLine>> = mNewsList

    private var mErrorMessage: MutableLiveData<String> = MutableLiveData()
    val errorMessage: LiveData<String> = mErrorMessage

    fun loadNewsList() {
        val headLineParameters = WeakHashMap<String, String>()
        headLineParameters["Language"] = Languages.EN.langName
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
}