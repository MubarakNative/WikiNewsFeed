package com.mubarak.wikinews.data.sources.remote.dto.newsfeed

import com.mubarak.wikinews.data.sources.remote.dto.mostread.MostRead
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.news.News
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.onthisday.Onthisday
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.tfa.Tfa
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsFeed(

    @SerialName("tfa")
    val todayFeaturedArticle: Tfa? = null,

    @SerialName("mostread")
    val mostRead: MostRead? = null, // news

    @SerialName("news")
    val news: List<News>?= null,

    @SerialName("onthisday")
    val onThisDay: List<Onthisday>? = null,

    )