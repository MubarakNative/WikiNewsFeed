package com.mubarak.wikinews.data.sources

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.sources.remote.dto.search.Page
import com.mubarak.wikinews.data.sources.remote.dto.search.SearchNews
import kotlinx.coroutines.flow.Flow

class DefaultNewsRepository(
   private val newsRemoteDataSource: NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNewsFeed(): NewsFeed {
        return newsRemoteDataSource.getNewsFeed()
    }

    override suspend fun getSearchNews(searchQuery:String): SearchNews {
        return newsRemoteDataSource.getSearchNews(searchQuery)
    }
}