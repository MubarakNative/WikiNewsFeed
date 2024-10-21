package com.mubarak.wikinews.data.network.models.dto.mostread.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultThumbnail(
    @SerialName("source")
    val imgUrl: String
)
