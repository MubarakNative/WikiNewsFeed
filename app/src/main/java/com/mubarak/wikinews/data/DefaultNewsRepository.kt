package com.mubarak.wikinews.data

import com.mubarak.wikinews.data.sources.NewsRemoteDataSource
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.sources.remote.dto.search.SearchNews

class DefaultNewsRepository(
   private val newsRemoteDataSource: NewsRemoteDataSource
): NewsRepository {
    override suspend fun getNewsFeed(): NewsFeed {
        return newsRemoteDataSource.getNewsFeed()
    }

    override suspend fun getSearchNews(searchQuery:String): SearchNews {
        return newsRemoteDataSource.getSearchNews(searchQuery)
    }
}