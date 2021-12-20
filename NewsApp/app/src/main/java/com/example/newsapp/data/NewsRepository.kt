package com.example.newsapp.data

import com.example.newsapp.network.NewsAPI
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsAPI
)  {

    suspend  fun getTopStories() = api.getTopStories()

    suspend fun  getNewsItemDetails(id: Int)  = api.getNewsItemDetails(id)

}