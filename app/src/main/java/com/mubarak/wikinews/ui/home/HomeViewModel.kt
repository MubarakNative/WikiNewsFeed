package com.mubarak.wikinews.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.wikinews.data.NewsRepository
import com.mubarak.wikinews.data.network.models.dto.newsfeed.NewsFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface HomeUiState {
    data class Success(val newsFeed: NewsFeed) : HomeUiState
    data object Error : HomeUiState
    data object Loading : HomeUiState
}

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    var newsUiState: HomeUiState by mutableStateOf(HomeUiState.Loading)
        private set

    init {
        getNewsFeed()
    }

    private fun getNewsFeed() {
        viewModelScope.launch {
            newsUiState = HomeUiState.Loading
            newsUiState = try {
                HomeUiState.Success(newsRepository.getNewsFeed())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e: Exception) {
                HomeUiState.Error
            }
        }
    }

}