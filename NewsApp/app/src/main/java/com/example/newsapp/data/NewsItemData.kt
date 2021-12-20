package com.example.newsapp.data


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(tableName = "news")
data class NewsItemData(
    @Json(name = "id")
    val id: Int,
    @Json(name = "by")
    val `by`: String,
    @Json(name = "descendants")
    val descendants: Int,
    @Json(name = "score")
    val score: Int,
    @Json(name = "time")
    val time: Int,
    @Json(name = "title")
    val title: String,
    @Json(name = "type")
    val type: String,
    @Json(name = "url")
    val url: String
)