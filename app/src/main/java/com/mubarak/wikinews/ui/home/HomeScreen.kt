package com.mubarak.wikinews.ui.home

import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.TopAppBarState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mubarak.wikinews.R
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.mostread.article.Article
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.news.News
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.onthisday.Onthisday
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.tfa.Tfa
import com.mubarak.wikinews.utils.TimeStampConvertor

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.newsUiState
    HomeScreen(uiState = uiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        HomeTopAppBar(scrollBehavior = scrollBehavior)
    }) {
        when (uiState) {
            HomeUiState.Error -> {
                // TODO: show error screen
                Toast.makeText(context, "Some sort of error Happened!\n Check Internet Connection", Toast.LENGTH_SHORT).show()
            }

            HomeUiState.Loading -> {
                NewsLoadingScreen()
            }

            is HomeUiState.Success -> {
                NewsFeed(
                    newsFeed = uiState.newsFeed,
                    modifier = Modifier.padding(it)
                )
            }
        }

    }
}

@Composable
fun NewsFeed(
    modifier: Modifier = Modifier,
    newsFeed: NewsFeed,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
    ) {
        item {
            TfaSection(tfa = newsFeed.todayFeaturedArticle) // tfa Section main
        }

        val onThisDayNews = newsFeed.onThisDay ?: emptyList()
        if (onThisDayNews.isNotEmpty() && onThisDayNews[0].pages.isNotEmpty()) {
            item {
                OnThisDaySection(onThisDay = onThisDayNews.take(6))
            }
        }

        val newsFeedArticle = newsFeed.news ?: emptyList()
        if (newsFeedArticle.isNotEmpty()) { // News section contains approx: 4
            item {
                FeedListNewsSection(news = newsFeedArticle) // news
            }
        }

        val mostReadArticles = newsFeed.mostRead?.articles ?: emptyList()
        if (mostReadArticles.isNotEmpty()) {
            items(mostReadArticles.take(8)){
                MostReadArticleSection(article = it)
            }
        }
    }
}

@Composable
fun MostReadArticleSection(
    modifier: Modifier = Modifier,
    article:Article
) {
        MostReadArticles(article = article, modifier = modifier)
        NewsItemDivider()
}

@Composable
fun MostReadArticles(
    modifier: Modifier = Modifier,
    article: Article
) {

    val timeStamp = remember(article.timestamp) {
        TimeStampConvertor.formatTimestampToUtc(article.timestamp)
    }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val imgModifier = Modifier
            .heightIn(min = 180.dp, max = 200.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.small)
            .blur(90.dp) // change it to appropriate

        NewsFeedImage(
            modifier = imgModifier,
            imgUrl = article.thumbnail?.imgUrl
        )
        Spacer(modifier = Modifier.height(16.dp))

        NewsTitle(
            text = article.longDescription,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = timeStamp,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Composable
fun OnThisDaySection(modifier: Modifier = Modifier, onThisDay: List<Onthisday>) {
    for (onThisDayItem in onThisDay) {
        OnThisDaySection(onThisDay = onThisDayItem, modifier = modifier)
        NewsItemDivider()
    }
}

@Composable
fun NewsLoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(64.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }

}

@Composable
fun TfaSection(
    modifier: Modifier = Modifier,
    tfa: Tfa?,
) {
    NewsTitle(
        text = stringResource(id = R.string.today_featured_article),
        color = MaterialTheme.colorScheme.onTertiaryContainer,
        modifier = modifier.padding(
            start = 16.dp, top = 16.dp, end = 16.dp
        )
    )

    tfa?.let {
        TodayFeaturedArticle(tfa = tfa, modifier = Modifier.clickable { })
    }

    NewsItemDivider()

    NewsTitle(
        text = stringResource(id = R.string.onThis_day_happened),
        color = MaterialTheme.colorScheme.onTertiaryContainer,
        modifier = Modifier.padding(16.dp)
    )
}


@Composable
fun FeedListNewsSection(
    modifier: Modifier = Modifier, news: List<News>
) {
    Column {
        NewsTitle(
            modifier = modifier.padding(start = 16.dp, top = 16.dp),
            text = stringResource(id = R.string.today_hot_topic)
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .height(IntrinsicSize.Max)
                .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (newsItem in news) {
                if (newsItem.newsArticles.isNotEmpty()) {
                    NewsArticlesFeed(news = newsItem.newsArticles[0], modifier = modifier)
                }
            }
        }

        NewsTitle(
            text = stringResource(id = R.string.most_read),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            modifier = Modifier.padding(
                start = 16.dp, top = 16.dp, end = 16.dp
            )
        )
    }
}

@Composable
fun NewsItemDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.07f)
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
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
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge
            )
        },
        actions = {
            IconButton(onClick = searchActionClick) {
                Icon(
                    imageVector = Icons.Outlined.Search, contentDescription = null
                )
            }
        },
        scrollBehavior = scrollBehavior
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun HomeTopAppBarPreview() {
    HomeTopAppBar()
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun LoadingScreenPreview() {
    NewsLoadingScreen()
}