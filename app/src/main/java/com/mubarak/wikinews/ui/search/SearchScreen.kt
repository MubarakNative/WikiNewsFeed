package com.mubarak.wikinews.ui.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mubarak.wikinews.R
import com.mubarak.wikinews.data.sources.remote.dto.search.Page
import com.mubarak.wikinews.ui.home.NewsFeedImage
import com.mubarak.wikinews.ui.home.NewsLoadingScreen
import com.mubarak.wikinews.ui.home.NewsTitle

@Composable
fun SearchRoute(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val searchResultUiState = searchViewModel.searchNewsUiState
    val searchQuery by searchViewModel.searchQuery.collectAsState()

    SearchScreen(
        modifier = modifier,
        searchResultUiState = searchResultUiState,
        searchQuery = searchQuery,
        onSearchQueryChanged = { searchViewModel.onSearchQueryChanged(it) },
        onSearchTriggered = { searchViewModel.getNewsFeed(it) }, // TODO: Implement auto search suggestion
        onBackClick = onBackClick
    )
}

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    searchResultUiState: SearchResultUiState = SearchResultUiState.Loading,
    searchQuery: String = "",
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.windowInsetsTopHeight(WindowInsets.safeDrawing))
        SearchToolbar(
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered,
            onBackClick = onBackClick
        )
        when (searchResultUiState) {
            SearchResultUiState.Loading -> {
                NewsLoadingScreen()
            }

            SearchResultUiState.Error,
            -> Unit

            SearchResultUiState.EmptyQuery -> {
                EmptySearchResultBody(searchQuery = searchQuery)
            }

            is SearchResultUiState.Success -> {
                if (searchResultUiState.isEmpty()) {
                    EmptySearchResultBody(searchQuery = searchQuery)
                } else {
                    SearchResultSection(page = searchResultUiState.searchNews)
                }
            }
        }
        Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.safeDrawing))

    }
}

@Composable
private fun SearchToolbar(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth()
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(id = R.string.back)
            )
        }
        SearchTextField(
            searchQuery = searchQuery,
            onSearchQueryChanged = onSearchQueryChanged,
            onSearchTriggered = onSearchTriggered
        )
    }
}

@Composable
fun SearchResultSection(
    modifier: Modifier = Modifier,
    page: List<Page> = emptyList()
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
    ) {
        if (page.isNotEmpty()) {
            items(page) {
                SearchResultContent(page = it)
            }
        }
    }
}

@Composable
fun SearchResultContent(
    modifier: Modifier = Modifier,
    page: Page
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) { // TODO: provide accessibility service to consider this as a whole item

        NewsFeedImage(
            modifier = Modifier
                .padding(16.dp)
                .size(80.dp, 80.dp)
                .clip(MaterialTheme.shapes.small),
            imgUrl = "https:${page.thumbnail?.url}" // all pages list have same sort of news
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f),
        ) {
            NewsTitle(
                modifier = Modifier.padding(end = 16.dp),
                text = page.title
            )

            Spacer(modifier = Modifier.height(10.dp))
            Text(
                // description
                text = page.description ?: stringResource(id = R.string.no_description),
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(end = 16.dp),
            )
        }
    }
}

@Composable
fun EmptySearchResultBody(
    modifier: Modifier = Modifier,
    searchQuery: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 48.dp),
    ) {
        val message =
            stringResource(id = R.string.no_result_found, searchQuery)
        val start = message.indexOf(searchQuery)
        Text(
            text = AnnotatedString(
                text = message,
                spanStyles = listOf(
                    AnnotatedString.Range(
                        SpanStyle(fontWeight = FontWeight.Bold),
                        start = start,
                        end = start + searchQuery.length,
                    ),
                ),
            ),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 24.dp),
        )
    }
}

@Composable
private fun SearchTextField(
    searchQuery: String,
    onSearchQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit
) {
    val focusRequester = remember {
        FocusRequester()
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSearchExplicitlyTriggered = {
        keyboardController?.hide()
        onSearchTriggered(searchQuery)
    }

    TextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = stringResource(
                    id = R.string.search_title,
                ),
                tint = MaterialTheme.colorScheme.onSurface,
            )
        },
        trailingIcon = {
            if (searchQuery.isNotEmpty()) {
                IconButton(onClick = {
                    onSearchQueryChanged("")
                }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(
                            id = R.string.search_title_clear,
                        ),
                        tint = MaterialTheme.colorScheme.onSurface,
                    )
                }
            }
        },
        onValueChange = {
            if ("\n" !in it) onSearchQueryChanged(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .focusRequester(focusRequester)
            .onKeyEvent {
                if (it.key == Key.Enter) {
                    onSearchExplicitlyTriggered()
                    true
                } else {
                    false
                }
            },
        shape = RoundedCornerShape(32.dp),
        value = searchQuery,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSearchExplicitlyTriggered()
            },
        ),
        maxLines = 1,
        singleLine = true,
    )
    LaunchedEffect(Unit) { // request keyboard on call
        focusRequester.requestFocus()
    }
}