package com.mubarak.wikinews.data.network.models.dto.newsfeed.news


import com.mubarak.wikinews.data.network.models.dto.NewsArticles
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class News(
    @SerialName("links")
    val newsArticles: List<NewsArticles>, // it is just links in a api
    @SerialName("story")
    val story: String
)