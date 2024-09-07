package com.mubarak.wikinews.data.sources.remote.dto.newsfeed.onthisday


import com.mubarak.wikinews.data.sources.remote.dto.Page
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Onthisday(

    @SerialName("pages")
    val pages: List<Page>,

    @SerialName("text")
    val newsText: String,

    @SerialName("year")
    val yearAtHappend: Int
)