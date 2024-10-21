package com.mubarak.wikinews.data.network.models.dto.newsfeed.news

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsArticleThumnail(
    @SerialName("source")
    val imgUrl: String
)