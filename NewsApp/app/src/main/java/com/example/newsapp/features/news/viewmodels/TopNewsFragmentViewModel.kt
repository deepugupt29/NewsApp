package com.example.newsapp.features.news.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.example.newsapp.data.NewsItemData
import com.example.newsapp.data.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class TopNewsFragmentViewModel @Inject constructor(private val newsRepo : NewsRepository) : ViewModel() {

    private val newIdsList = MutableLiveData<List<Int>>()

    val newsIds : LiveData<List<Int>>
        get() = newIdsList


    fun getTopStories() {
        viewModelScope.launch() {
            newsRepo.getTopStories().let { response ->
                if (response.isSuccessful) {
                    newIdsList.postValue(response.body())
                }
            }

        }
    }

    private val newsItem = MutableLiveData<NewsItemData>()

    val newsItemDetails : LiveData<NewsItemData>
        get() = newsItem

    fun getNewsItemsDetails(id : Int) {
        viewModelScope.launch() {
            newsRepo.getNewsItemDetails(id).let { response ->
                if (response.isSuccessful) {
                    newsItem.postValue(response.body())
                }
            }

        }
    }

}