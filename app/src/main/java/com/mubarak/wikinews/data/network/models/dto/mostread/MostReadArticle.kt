package com.mubarak.wikinews.data.network.models.dto.mostread

import com.mubarak.wikinews.data.network.models.dto.mostread.article.Article
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MostRead(
    @SerialName("articles")
    val articles: List<Article>,
    @SerialName("date")
    val date: String
)