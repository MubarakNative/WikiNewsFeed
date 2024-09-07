package com.mubarak.wikinews.data.sources.remote.dto.newsfeed.onthisday

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PageThumnail(
    @SerialName("source")
    val imgUrl: String
)