package com.mubarak.wikinews.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class ArticleEntity(

    @PrimaryKey
    @ColumnInfo("id")
    val id: Long,

    @ColumnInfo(name = "description")
    val longDescription: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "image_url")
    val thumbnail: String? = null,

    @ColumnInfo(name = "date")
    val timestamp: String,
)
