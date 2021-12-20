package com.example.newsapp.network

import com.example.newsapp.data.NewsItemData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsAPI {

    @GET("v0/topstories.json?print=pretty")
    suspend fun getTopStories(): Response<List<Int>>

    @GET("v0/item/{articleid}.json?print=pretty")
    suspend fun getNewsItemDetails(@Path("articleid") id: Int): Response<NewsItemData>
}