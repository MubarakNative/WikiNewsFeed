package com.mubarak.wikinews.data.sources.remote.dto.search


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Thumbnail(
    @SerialName("height")
    val height: Int,

    @SerialName("mimetype")
    val mimetype: String,

    @SerialName("url")
    val url: String,

    @SerialName("width")
    val width: Int
)