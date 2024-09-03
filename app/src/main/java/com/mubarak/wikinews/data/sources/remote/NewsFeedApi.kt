package com.mubarak.wikinews.data.sources.remote

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed

interface NewsFeedApi {
    suspend fun getNewsFeed(): NewsFeed
}