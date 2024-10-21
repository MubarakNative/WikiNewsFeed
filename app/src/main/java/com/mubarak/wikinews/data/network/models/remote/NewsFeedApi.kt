package com.mubarak.wikinews.data.network.models.remote

import com.mubarak.wikinews.data.network.models.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.network.models.dto.search.Page
import com.mubarak.wikinews.data.network.models.dto.search.SearchNews
import kotlinx.coroutines.flow.Flow

interface NewsFeedApi {
    suspend fun getNewsFeed(): NewsFeed // Featured content endpoint
    suspend fun getSearchNews(searchQuery:String): SearchNews // Search content endpoint
}