package com.mubarak.wikinews.data.network.models.dto.mostread.article


import com.mubarak.wikinews.data.network.models.dto.ContentUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("content_urls")
    val contentUrls: ContentUrls,

    @SerialName("pageid")
    val pageId: Long,

    @SerialName("description")
    val description: String? = null,

    @SerialName("extract")
    val longDescription: String,

    @SerialName("normalizedtitle")
    val normalizedTitle: String,

    @SerialName("thumbnail")
    val thumbnail: ArticleThumbnail? = null,

    @SerialName("originalimage")
    val defaultThumbnail: DefaultThumbnail? = null,

    @SerialName("tid")
    val tid: String,

    @SerialName("timestamp")
    val timestamp: String,

    @SerialName("title")
    val title: String,

    )