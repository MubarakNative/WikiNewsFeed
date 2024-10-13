package com.mubarak.wikinews.data.sources.remote.dto.newsfeed.mostread.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleThumbnail(
    @SerialName("source")
    val imgUrl: String
)