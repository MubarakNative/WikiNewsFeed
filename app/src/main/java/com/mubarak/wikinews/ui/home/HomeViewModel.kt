package com.mubarak.wikinews.ui.home

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.wikinews.data.sources.NewsRepository
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface HomeUiState {
    data class Success(val newsFeed: NewsFeed) : HomeUiState
    data object Error : HomeUiState
    data object Loading : HomeUiState
}

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    var newsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getNewsFeed()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun getNewsFeed() {
        viewModelScope.launch {
            newsUiState = HomeUiState.Loading
            newsUiState = try {
                HomeUiState.Success(newsRepository.getNewsFeed())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: Exception) {
                HomeUiState.Error
            } catch (e: HttpException) {
                HomeUiState.Error
            }
        }
    }

}