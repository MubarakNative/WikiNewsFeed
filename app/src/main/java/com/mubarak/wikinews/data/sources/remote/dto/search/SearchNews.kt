package com.mubarak.wikinews.data.sources.remote.dto.search


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchNews(
    @SerialName("pages")
    val pages: List<Page>
)