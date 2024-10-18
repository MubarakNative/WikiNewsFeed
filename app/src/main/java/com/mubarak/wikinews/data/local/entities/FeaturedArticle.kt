package com.mubarak.wikinews.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "featured_article")
data class FeaturedArticle(

    @PrimaryKey
    @ColumnInfo("id")
    val id: Long,

    @ColumnInfo(name = "article_description")
    val description: String,

    @ColumnInfo(name = "image_url")
    val headerUrl: String? = null,

    @ColumnInfo(name = "timeStamp")
    val timeStamp: String
)