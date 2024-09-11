package com.mubarak.wikinews.data.sources.remote

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.utils.AppConstant.ACCESS_TOKEN
import com.mubarak.wikinews.utils.DateFormatter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
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
                   path("feed/v1/wikipedia/en/featured/${DateFormatter.formattedDate}") // TODO: replace with current dynamic date
               }
                header("Authorization","Bearer $ACCESS_TOKEN")
            }
        }.body()
    }
}