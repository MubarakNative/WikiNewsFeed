package com.mubarak.wikinews.data.network.models.dto.mostread.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleThumbnail(
    @SerialName("source")
    val imgUrl: String
)