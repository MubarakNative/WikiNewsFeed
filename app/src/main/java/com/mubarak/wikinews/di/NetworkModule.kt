package com.mubarak.wikinews.di

import com.mubarak.wikinews.data.sources.DefaultNewsRepository
import com.mubarak.wikinews.data.sources.NewsRemoteDataSource
import com.mubarak.wikinews.data.sources.NewsRepository
import com.mubarak.wikinews.data.sources.remote.DefaultNewsFeedApi
import com.mubarak.wikinews.data.sources.remote.NewsFeedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import java.net.InetSocketAddress
import java.net.Proxy
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideHttpClient(): HttpClient {
        return HttpClient(Android) {
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                })
            }
            install(Logging) {
                logger = Logger.ANDROID // logs on LogCat
              //  level = LogLevel.ALL
            }
        }
    }

    @Singleton
    @Provides
    fun provideNewsFeedApi(httpClient: HttpClient): NewsFeedApi =
        DefaultNewsFeedApi(httpClient)

    @Provides
    @Singleton
    fun provideNewsRemoteDataSource(newsFeedApi: NewsFeedApi): NewsRemoteDataSource =
        NewsRemoteDataSource(newsFeedApi)

    @Singleton
    @Provides
    fun provideNewsRepository(newsRemoteDataSource: NewsRemoteDataSource): NewsRepository =
        DefaultNewsRepository(newsRemoteDataSource)

}