package com.mubarak.wikinews.ui.breaking

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.wikinews.data.NewsRepository
import com.mubarak.wikinews.data.network.models.remote.NewsFeedApi
import com.mubarak.wikinews.data.network.models.dto.newsfeed.NewsFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface MostReadUiState {
    data class Success(val newsFeed: NewsFeed) : MostReadUiState
    data object Error : MostReadUiState
    data object Loading : MostReadUiState
}

@HiltViewModel
class MostReadViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {
    var mostReadUiState: MostReadUiState by mutableStateOf(MostReadUiState.Loading)
        private set

    init {
        getMostReadFeed()
    }

    private fun getMostReadFeed() {
        viewModelScope.launch {
            mostReadUiState = MostReadUiState.Loading
            mostReadUiState = try {
                MostReadUiState.Success(newsRepository.getNewsFeed())
            } catch (e: IOException) {
                MostReadUiState.Error
            } catch (e: Exception) {
                MostReadUiState.Error
            }
        }
    }
}