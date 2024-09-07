package com.mubarak.wikinews.data.sources.remote.dto.newsfeed.mostread.article


import com.mubarak.wikinews.data.sources.remote.dto.ContentUrls
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("content_urls")
    val contentUrls: ContentUrls,

  //  @SerialName("description")
 //   val description: String?,

    @SerialName("extract")
    val longDescription: String,

    @SerialName("normalizedtitle")
    val normalizedTitle: String,

 //   @SerialName("thumbnail")
   // val thumbnail: ArticleThumnail?,

    @SerialName("tid")
    val tid: String,

    @SerialName("timestamp")
    val timestamp: String,

    @SerialName("title")
    val title: String,

    )