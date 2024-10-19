package com.mubarak.wikinews.ui.breaking

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mubarak.wikinews.R
import com.mubarak.wikinews.data.sources.remote.dto.mostread.article.Article
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.ui.home.NewsFeedImage
import com.mubarak.wikinews.ui.home.NewsItemDivider
import com.mubarak.wikinews.ui.home.NewsLoadingScreen
import com.mubarak.wikinews.ui.home.NewsTitle
import com.mubarak.wikinews.ui.home.launchCustomChromeTab
import com.mubarak.wikinews.utils.TimeStampConvertor

@Composable
fun MostReadRoute(
    modifier: Modifier = Modifier, onSearchActionClick: () -> Unit,
    viewModel: MostReadViewModel = hiltViewModel()
) {
    val mostReadFeed = viewModel.mostReadUiState
    MostReadScreen(
        uiState = mostReadFeed,
        onSearchActionClick = onSearchActionClick,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostReadScreen(
    modifier: Modifier = Modifier,
    onSearchActionClick: () -> Unit, uiState: MostReadUiState
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = modifier, topBar = {
        MostReadAppBar(scrollBehavior = scrollBehavior, searchActionClick = onSearchActionClick)
    }) {
        when (uiState) {
            MostReadUiState.Error -> {
                Toast.makeText(
                    context, stringResource(id = R.string.error_msg), Toast.LENGTH_SHORT
                ).show()
            }

            MostReadUiState.Loading -> {
                NewsLoadingScreen()
            }

            is MostReadUiState.Success -> {
                MostReadFeed(
                    modifier = Modifier.padding(it),
                    newsFeed = uiState.newsFeed
                )
            }
        }

    }
}

@Composable
fun MostReadFeed(
    modifier: Modifier = Modifier,
    newsFeed: NewsFeed,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 250.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        val mostReadArticles = newsFeed.mostRead?.articles ?: emptyList()
        if (mostReadArticles.isNotEmpty()) {
            items(mostReadArticles) {
                MostReadArticleSection(article = it)
            }
        }
    }
}


@Composable
fun MostReadArticleSection(
    modifier: Modifier = Modifier, article: Article
) {
    val context = LocalContext.current
    MostReadArticles(article = article, modifier = modifier, onClick = {
        launchCustomChromeTab(
            loadUrl = article.contentUrls.mobile.pageUrl,
            context = context
        )
    })
    NewsItemDivider()
}

@Composable
fun MostReadArticles(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    article: Article
) {

    val timeStamp = remember(article.timestamp) {
        TimeStampConvertor.formatTimestampToUtc(article.timestamp)
    }

    val clickActionLabel = stringResource(id = R.string.open_article_detail)
    Card(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.semantics {
            onClick(label = clickActionLabel, action = null)
        },
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            val imgModifier = Modifier
                .heightIn(min = 140.dp, max = 160.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.medium)

            NewsFeedImage(
                modifier = imgModifier,
                imgUrl = article.thumbnail?.imgUrl,
                contentDescription = article.normalizedTitle
            )
            Spacer(modifier = Modifier.height(16.dp))

            NewsTitle(
                text = article.longDescription, modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = timeStamp, style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MostReadAppBar(
    modifier: Modifier = Modifier,
    scrollState: TopAppBarState = rememberTopAppBarState(),
    searchActionClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(scrollState)
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = stringResource(id = R.string.most_read_title),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = searchActionClick) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(id = R.string.search)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun MostReadTopAppBarPreview() {
    MostReadAppBar()
}