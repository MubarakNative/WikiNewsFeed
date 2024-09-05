package com.mubarak.wikinews.data.sources

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed

class DefaultNewsRepository(
   private val newsRemoteDataSource: NewsRemoteDataSource
):NewsRepository {
    override suspend fun getNewsFeed(): NewsFeed {
        return newsRemoteDataSource.getNewsFeed()
    }
}