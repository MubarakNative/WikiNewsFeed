package com.mubarak.wikinews.data.sources.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    @SerialName("source")
    val imgUrl: String
)