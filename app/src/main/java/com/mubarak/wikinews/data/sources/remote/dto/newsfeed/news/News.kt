package com.mubarak.wikinews.data.sources.remote.dto.newsfeed.news


import com.mubarak.wikinews.data.sources.remote.dto.NewsArticles
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    @SerialName("links")
    val newsArticles: List<NewsArticles>,
    @SerialName("story")
    val story: String
)