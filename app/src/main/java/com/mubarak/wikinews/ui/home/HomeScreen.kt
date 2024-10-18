package com.mubarak.wikinews.ui.home

import android.content.Context
import android.content.res.Configuration
import android.net.Uri
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.browser.customtabs.CustomTabsIntent
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
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

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun HomeRoute(
    modifier: Modifier = Modifier,
    onSearchActionClick: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val newsUiState = viewModel.newsUiState
    HomeScreen(
        modifier = modifier, uiState = newsUiState, onSearchActionClick = onSearchActionClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, onSearchActionClick: () -> Unit, uiState: HomeUiState
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        HomeTopAppBar(scrollBehavior = scrollBehavior, searchActionClick = onSearchActionClick)
    }) {
        when (uiState) {
            HomeUiState.Error -> {
                // TODO: show error screen
                Toast.makeText(
                    context, stringResource(id = R.string.error_msg), Toast.LENGTH_SHORT
                ).show()
            }

            HomeUiState.Loading -> {
                NewsLoadingScreen()
            }

            is HomeUiState.Success -> {
                NewsFeed(
                    newsFeed = uiState.newsFeed, modifier = Modifier.padding(it)
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
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 250.dp),
        contentPadding = PaddingValues(0.dp),
    ) {
        item(span = {
            GridItemSpan(maxLineSpan)
        }) {
            TfaSection(tfa = newsFeed.todayFeaturedArticle) // tfa Section main
        }

        val onThisDayNews = newsFeed.onThisDay ?: emptyList()
        if (onThisDayNews.isNotEmpty() && onThisDayNews[0].pages.isNotEmpty()) {
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                OnThisDaySection(onThisDay = onThisDayNews)
            }
        }

        val newsFeedArticle = newsFeed.news ?: emptyList()
        if (newsFeedArticle.isNotEmpty()) { // News section contains approx: 4
            item(span = {
                GridItemSpan(maxLineSpan)
            }) {
                FeedListNewsSection(news = newsFeedArticle) // news
            }
        }

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

@Composable
fun OnThisDaySection(modifier: Modifier = Modifier, onThisDay: List<Onthisday>) {

    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.onThis_day_happened),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )

        for (onThisDayItem in onThisDay) {
            OnThisDaySection(onThisDay = onThisDayItem, modifier = modifier, onClick = {
                launchCustomChromeTab(
                    loadUrl = onThisDayItem.pages[0].contentUrls.mobile.pageUrl,
                    context = context
                )
            })
            NewsItemDivider()
        }
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
    val context = LocalContext.current
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = stringResource(id = R.string.today_featured_article),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )
        tfa?.let {
            TodayFeaturedArticle(tfa = tfa, onClick = {
                launchCustomChromeTab(
                    loadUrl = tfa.contentUrls.mobile.pageUrl,
                    context = context
                )
            })
        }
    }

    NewsItemDivider()

}


@Composable
fun FeedListNewsSection(
    modifier: Modifier = Modifier, news: List<News>
) {
    val context = LocalContext.current
    Column {
        Text(
            text = stringResource(id = R.string.today_hot_topic),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
        )

        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .height(IntrinsicSize.Max)
                .padding(16.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (newsItem in news) {
                if (newsItem.newsArticles.isNotEmpty()) {
                    val newsArticle = newsItem.newsArticles[0]
                    NewsArticlesFeed(news = newsArticle, modifier = modifier, onClick = {
                        launchCustomChromeTab(
                            loadUrl = newsArticle.contentUrls.mobile.pageUrl,
                            context = context
                        )
                    })
                }
            }
        }
        Text(
            text = stringResource(id = R.string.most_read),
            color = MaterialTheme.colorScheme.onTertiaryContainer,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(8.dp)
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
                    imageVector = Icons.Outlined.Search,
                    contentDescription = stringResource(id = R.string.search)
                )
            }
        },
        scrollBehavior = scrollBehavior
    )

}

fun launchCustomChromeTab(loadUrl: String, context: Context) {
    val intent = CustomTabsIntent.Builder()
        .setShowTitle(true)
        .setUrlBarHidingEnabled(true)
        .build()
    intent.launchUrl(context, Uri.parse(loadUrl))
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