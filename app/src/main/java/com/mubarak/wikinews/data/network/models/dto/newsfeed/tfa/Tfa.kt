package com.mubarak.wikinews.data.network.models.dto.newsfeed.tfa

import com.mubarak.wikinews.data.network.models.dto.Thumbnail
import com.mubarak.wikinews.data.network.models.dto.Url
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Tfa( // today featured content
    @SerialName("content_urls")
    val contentUrls: Url,

    @SerialName("pageid")
    val pageId: Long,

    @SerialName("description")
    val description: String,

    @SerialName("extract")
    val largeDescription: String,

    @SerialName("normalizedtitle")
    val normalizedTitle: String,

    @SerialName("thumbnail")
    val thumbnail: Thumbnail?= null,

    @SerialName("tid")
    val tid: String,

    @SerialName("timestamp")
    val timestamp: String,

    @SerialName("title")
    val title: String,

    @SerialName("type")
    val type: String,

    @SerialName("wikibase_item")
    val wikibaseItem: String
)