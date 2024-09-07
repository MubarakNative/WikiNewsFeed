package com.mubarak.wikinews.data.sources.remote.dto


import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.news.NewsArticleThumnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsArticles(
    @SerialName("content_urls")
    val contentUrls: NewsContentUrl,

    @SerialName("description")
    val description: String,

    @SerialName("extract")
    val longDescription: String,

    @SerialName("normalizedtitle")
    val normalizedTitle: String,

    @SerialName("thumbnail")
    val thumbnail: NewsArticleThumnail? = null,

    @SerialName("tid")
    val tid: String,

    @SerialName("timestamp")
    val timestamp: String,

    @SerialName("title")
    val title: String,

    )