package com.mubarak.wikinews.data.network.models.dto


import com.mubarak.wikinews.data.network.models.dto.newsfeed.onthisday.PageThumnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Page(

    @SerialName("content_urls")
    val contentUrls: Url,

    @SerialName("description")
    val description: String? = null,

    @SerialName("extract")
    val longDescription: String,

    @SerialName("normalizedtitle")
    val normalizedTitle: String,

    @SerialName("thumbnail")
    val thumbnail: PageThumnail? = null,

    @SerialName("pageid")
    val pageId:Long,

    @SerialName("tid")
    val tid: String,

    @SerialName("timestamp")
    val timestamp: String,

    )