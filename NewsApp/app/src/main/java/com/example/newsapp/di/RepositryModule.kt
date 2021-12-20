package com.example.newsapp.di

import com.example.newsapp.network.NewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RepositryModule {

    val BASE_URL = "https://hacker-news.firebaseio.com/"

    @Provides
    @Singleton
    fun providesNewsAPI(client: Retrofit) : NewsAPI {
        return client.create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun Retrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}