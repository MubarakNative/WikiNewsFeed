package com.mubarak.wikinews.data.sources.remote

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.sources.remote.dto.search.Page
import com.mubarak.wikinews.data.sources.remote.dto.search.SearchNews
import kotlinx.coroutines.flow.Flow

interface NewsFeedApi {
    suspend fun getNewsFeed(): NewsFeed // Featured content endpoint
    suspend fun getSearchNews(searchQuery:String): SearchNews // Search content endpoint
}