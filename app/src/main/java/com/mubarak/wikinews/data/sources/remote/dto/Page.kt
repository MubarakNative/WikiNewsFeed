package com.mubarak.wikinews.data.sources.remote.dto


import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.onthisday.PageThumnail
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Page(

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