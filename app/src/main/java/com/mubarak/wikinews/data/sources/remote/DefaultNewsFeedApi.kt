package com.mubarak.wikinews.data.sources.remote

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.URLProtocol
import io.ktor.http.path

class DefaultNewsFeedApi(
    private val httpClient: HttpClient
) : NewsFeedApi {
    override suspend fun getNewsFeed(): NewsFeed {
       return httpClient.use {
            it.get{ // GET method
               url { // configure the URL components separately
                   protocol = URLProtocol.HTTPS
                   host = "api.wikimedia.org"
                   path("feed/v1/wikipedia/en/featured/2024/09/09") // TODO: replace with current dynamic date
               }
            }
        }.body()
    }
}