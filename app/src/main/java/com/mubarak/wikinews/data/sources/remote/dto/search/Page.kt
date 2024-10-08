package com.mubarak.wikinews.data.sources.remote.dto.search


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Page(
    @SerialName("description")
    val description: String?,

    @SerialName("excerpt")
    val excerpt: String,

    @SerialName("id")
    val id: Int,

    @SerialName("key")
    val key: String,

    @SerialName("thumbnail")
    val thumbnail: Thumbnail?,

    @SerialName("title")
    val title: String
)