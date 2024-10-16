package com.mubarak.wikinews.data

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.sources.remote.dto.search.SearchNews

interface NewsRepository {
    suspend fun getNewsFeed(): NewsFeed
   suspend fun getSearchNews(searchQuery:String): SearchNews
}