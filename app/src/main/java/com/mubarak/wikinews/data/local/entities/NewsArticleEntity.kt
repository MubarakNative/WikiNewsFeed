package com.mubarak.wikinews.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_article")
data class NewsArticleEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,

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
