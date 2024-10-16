package com.mubarak.wikinews.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "wikinews_entity")
data class NewsFeedEntity(
    @Embedded
    val featured: FeaturedArticle,
    @Embedded
    val mostReadArticles: ArticleEntity? = null,
    @Embedded
    val todayNews: NewsArticleEntity? = null,
    @Embedded
    val onThisDayHappenedNews: OnThisDayEntity? = null
)
