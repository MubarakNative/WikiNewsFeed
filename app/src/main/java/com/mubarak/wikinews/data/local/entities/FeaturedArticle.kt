package com.mubarak.wikinews.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "featured_article")
data class FeaturedArticle(

@ColumnInfo(name = "article_description")
val description: String,

@ColumnInfo(name = "image_url")
val headerUrl: String? = null,

@ColumnInfo(name = "timeStamp")
val timeStamp: String
)