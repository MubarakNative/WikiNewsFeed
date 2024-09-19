package com.mubarak.wikinews.data.sources.remote

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.utils.AppConstant.ACCESS_TOKEN
import com.mubarak.wikinews.utils.DateFormatter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.URLProtocol
import io.ktor.http.appendEncodedPathSegments
import io.ktor.http.path

class DefaultNewsFeedApi(
    private val httpClient: HttpClient
) : NewsFeedApi {
    override suspend fun getNewsFeed(): NewsFeed {
        val currentDate = DateFormatter.formattedDate
       return httpClient.get {
            url{
                appendEncodedPathSegments("feed","v1","wikipedia","en","featured",currentDate)
                protocol = URLProtocol.HTTPS
                host = "api.wikimedia.org"
            }
            header("Authorization","Bearer $ACCESS_TOKEN")
        }.body()
    }

}