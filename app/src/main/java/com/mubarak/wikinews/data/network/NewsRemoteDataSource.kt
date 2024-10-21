package com.mubarak.wikinews.data.network

import com.mubarak.wikinews.data.network.models.remote.NewsFeedApi
import com.mubarak.wikinews.data.network.models.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.network.models.dto.search.SearchNews
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRemoteDataSource(
    private val newsFeedApi: NewsFeedApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getNewsFeed(): NewsFeed =
        withContext(dispatcher) {
            newsFeedApi.getNewsFeed()
        }

    suspend fun getSearchNews(searchQuery: String): SearchNews =
        newsFeedApi.getSearchNews(searchQuery)

}

