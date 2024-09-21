package com.mubarak.wikinews.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.wikinews.data.sources.NewsRepository
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import dagger.hilt.android.lifecycle.HiltViewModel
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
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

    private var _newsUiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val newsUiState = _newsUiState.onStart { getNewsFeed() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            HomeUiState.Loading)


    private fun getNewsFeed() {
        viewModelScope.launch {
            _newsUiState.value = HomeUiState.Loading
            _newsUiState.value = try {
                HomeUiState.Success(newsRepository.getNewsFeed())
            } catch (e: IOException) {
                HomeUiState.Error
            } catch (e:Exception){
                HomeUiState.Error
            }
        }
    }

    fun retryNews(){
        getNewsFeed()
    }

}