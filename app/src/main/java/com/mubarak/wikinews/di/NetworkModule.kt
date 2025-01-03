package com.mubarak.wikinews.di

import com.mubarak.wikinews.data.DefaultNewsRepository
import com.mubarak.wikinews.data.network.NewsRemoteDataSource
import com.mubarak.wikinews.data.NewsRepository
import com.mubarak.wikinews.data.network.models.remote.DefaultNewsFeedApi
import com.mubarak.wikinews.data.network.models.remote.NewsFeedApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
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
                level = LogLevel.ALL
            }

            engine {
                connectTimeout = 100_000
                socketTimeout = 100_000
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