package com.mubarak.wikinews.data.sources.remote

import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.sources.remote.dto.search.SearchNews
import com.mubarak.wikinews.utils.AppConstant.ACCESS_TOKEN
import com.mubarak.wikinews.utils.DateFormatter
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.appendEncodedPathSegments

class DefaultNewsFeedApi(
    private val httpClient: HttpClient
) : NewsFeedApi {
    override suspend fun getNewsFeed(): NewsFeed {
        val currentDate = DateFormatter.formattedDate
        return httpClient.get {
            url {
                appendEncodedPathSegments("feed", "v1", "wikipedia", "en", "featured", currentDate)
                protocol = URLProtocol.HTTPS
                host = "api.wikimedia.org"
            }
            header("Authorization", "Bearer $ACCESS_TOKEN")
        }.body()

    }

    override suspend fun getSearchNews(searchQuery: String): SearchNews {
        return httpClient.get {
            if (searchQuery.isNotEmpty()) {
                url {
                    appendEncodedPathSegments("core", "v1", "wikipedia", "en", "search", "title")
                    protocol = URLProtocol.HTTPS
                    host = "api.wikimedia.org"
                }
                parameter("q", searchQuery)
                parameter("limit", 20) // 20 items per pagination
            }
        }.body()
    }

}