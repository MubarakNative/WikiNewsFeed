package com.mubarak.wikinews.data.sources

import com.mubarak.wikinews.data.sources.remote.NewsFeedApi
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.sources.remote.dto.search.SearchNews
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsRemoteDataSource(
    private val newsFeedApi: NewsFeedApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun getNewsFeed(): NewsFeed =
        withContext(dispatcher){
            newsFeedApi.getNewsFeed()
        }
    suspend fun getSearchNews(): SearchNews =
        withContext(dispatcher){
            newsFeedApi.getSearchNews()
        }
}