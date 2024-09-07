package com.mubarak.wikinews.data.sources.remote.dto.newsfeed.mostread.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleThumnail(
    @SerialName("source")
    val imgUrl: String
)