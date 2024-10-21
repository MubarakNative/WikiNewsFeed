package com.mubarak.wikinews.ui.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mubarak.wikinews.data.NewsRepository
import com.mubarak.wikinews.data.network.models.dto.search.Page
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

sealed interface SearchResultUiState{
    /**
     * The searchquery is too small or empty to perform the search.
     * */
    data object EmptyQuery: SearchResultUiState

    data object Error : SearchResultUiState

    data object Loading : SearchResultUiState

    data class Success(
        val searchNews: List<Page> = emptyList()
    ) : SearchResultUiState{
        fun isEmpty(): Boolean = searchNews.isEmpty()
    }
}

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    private val savedStateHandle: SavedStateHandle
):ViewModel() {

    val searchQuery = savedStateHandle.getStateFlow(SEARCH_QUERY,"")

    var searchNewsUiState: SearchResultUiState by mutableStateOf(SearchResultUiState.Loading)
        private set

    init {
        getNewsFeed(searchQuery.value)
    }

     fun getNewsFeed(query: String) {
        viewModelScope.launch {
            searchNewsUiState = SearchResultUiState.Loading
            searchNewsUiState = try {
                SearchResultUiState.Success(newsRepository.getSearchNews(query).pages)
            } catch (e: IOException) {
                SearchResultUiState.Error
            } catch (e:Exception){
                SearchResultUiState.Error
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        savedStateHandle[SEARCH_QUERY] = query
    }
}

private const val SEARCH_QUERY = "searchQuery"