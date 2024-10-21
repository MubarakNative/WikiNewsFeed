package com.mubarak.wikinews.data.network.models.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    @SerialName("source")
    val imgUrl: String
)