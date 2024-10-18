package com.mubarak.wikinews.data.local.entities

import androidx.room.Embedded
import androidx.room.Entity

@Entity(tableName = "wikinews_entity")
data class NewsFeedEntity(
    @Embedded
    val featured: FeaturedArticle,
    @Embedded
    val mostReadArticles: ArticleEntity,
    @Embedded
    val todayNews: NewsArticleEntity,
    @Embedded
    val onThisDayHappenedNews: OnThisDayEntity
)
