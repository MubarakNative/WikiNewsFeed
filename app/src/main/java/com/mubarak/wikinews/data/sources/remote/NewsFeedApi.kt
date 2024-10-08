package com.mubarak.wikinews.data.sources.remote

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.sources.remote.dto.search.SearchNews

interface NewsFeedApi {
    suspend fun getNewsFeed(): NewsFeed // Featured content endpoint
    suspend fun getSearchNews(): SearchNews // Search content endpoint
}