package com.mubarak.wikinews.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class OnThisDayEntity(

    @ColumnInfo(name = "news_title")
    val title: String,

    @PrimaryKey
    @ColumnInfo("id")
    val id: Long,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo("image_url")
    val thumbnail: String? = null,

    @PrimaryKey
    @ColumnInfo("pageid")
    val pageId:Long,

    @ColumnInfo("date")
    val timestamp: String,
)
