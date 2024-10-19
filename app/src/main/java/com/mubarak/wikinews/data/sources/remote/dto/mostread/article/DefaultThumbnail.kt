package com.mubarak.wikinews.data.sources.remote.dto.mostread.article

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefaultThumbnail(
    @SerialName("source")
    val imgUrl: String
)
