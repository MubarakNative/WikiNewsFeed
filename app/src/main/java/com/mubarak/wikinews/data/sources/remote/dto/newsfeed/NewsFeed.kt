package com.mubarak.wikinews.data.sources.remote.dto.newsfeed

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.mostread.MostRead
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.news.News
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.onthisday.Onthisday
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.tfa.Tfa
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsFeed(

    @SerialName("mostread")
    val mostRead: MostRead, // news

    @SerialName("news")
    val news: List<News>,

    @SerialName("onthisday")
    val onThisDay: List<Onthisday>,

    @SerialName("tfa")
    val todayFeaturedArticle: Tfa
)