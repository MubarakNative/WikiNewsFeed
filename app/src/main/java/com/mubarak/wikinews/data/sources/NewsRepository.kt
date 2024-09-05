package com.mubarak.wikinews.data.sources

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed

interface NewsRepository {
    suspend fun getNewsFeed(): NewsFeed
}