package com.mubarak.wikinews.data

import com.mubarak.wikinews.data.network.models.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.network.models.dto.search.SearchNews

interface NewsRepository {
    suspend fun getNewsFeed(): NewsFeed
   suspend fun getSearchNews(searchQuery:String): SearchNews
}