package com.mubarak.wikinews.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "news_article")
data class NewsArticleEntity(

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "long_description")
    val longDescription: String,

    @ColumnInfo(name = "title")
    val normalizedTitle: String,

    @ColumnInfo(name = "image_url")
    val thumbnail: String? = null,

    @ColumnInfo(name = "date")
    val timestamp: String,
)
