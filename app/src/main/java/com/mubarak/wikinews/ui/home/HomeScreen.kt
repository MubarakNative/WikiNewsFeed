package com.mubarak.wikinews.ui.home

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.mubarak.wikinews.R
import com.mubarak.wikinews.data.sources.remote.dto.Mobile
import com.mubarak.wikinews.data.sources.remote.dto.NewsArticles
import com.mubarak.wikinews.data.sources.remote.dto.NewsContentUrl
import com.mubarak.wikinews.data.sources.remote.dto.Page
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.NewsFeed
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.news.News
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.news.NewsArticleThumnail
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.onthisday.Onthisday
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.onthisday.PageThumnail
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.tfa.Tfa

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun WikiNewsHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.newsUiState
    WikiNewsHomeScreen(uiState = uiState)
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WikiNewsHomeScreen(
    modifier: Modifier = Modifier,
    uiState: HomeUiState
) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(scrollBehavior = scrollBehavior)
        }
    ) {
        when (uiState) {
            HomeUiState.Error -> {
                Toast.makeText(context, "Some sort of error Happened!", Toast.LENGTH_SHORT).show()
            }

            HomeUiState.Loading -> {
                NewsLoadingScreen()
            }

            is HomeUiState.Success -> {
                NewsFeed(newsFeed = uiState.newsFeed, modifier = Modifier.padding(it))
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
        contentPadding = PaddingValues(0.dp)
    ) {
        item {
            TfaSection(tfa = newsFeed.todayFeaturedArticle) // tfa Section main
        }

        if (newsFeed.onThisDay!!.isNotEmpty()) {
            items(newsFeed.onThisDay) {
                key(it.pages[0].tid) {
                    OnThisDaySection(onThisDay = it, modifier = modifier)
                }
            }
        }

        if (newsFeed.news.isNotEmpty()) { // News section contains approx: 4
            item {
                FeedListNewsSection(news = newsFeed.news) // news
            }
        }
    }
}

@Composable
fun NewsLoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
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
    tfa: Tfa,

    ) {
    Text(
        modifier = Modifier.padding(
            start = 16.dp,
            top = 16.dp
        ), text = stringResource(id = R.string.today_featured_article),
        style = MaterialTheme.typography.titleLarge
    )

    TodayFeaturedArticle(tfa = tfa, modifier = Modifier.clickable { })
    NewsItemDivider()
}

@Composable
fun OnThisDaySection(
    modifier: Modifier = Modifier,
    onThisDay: Onthisday
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) { // TODO: provide accessibility service to consider this as a whole item

        AsyncImage(
            error = painterResource(id = R.drawable.ic_launcher_background),
            modifier = Modifier
                .padding(16.dp)
                .size(120.dp, 120.dp)
                .clip(MaterialTheme.shapes.medium),
            model = onThisDay.pages[0].thumbnail?.imgUrl, // all pages list have same sort of news
            contentScale = ContentScale.Crop,
            contentDescription = null
        )

        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(vertical = 10.dp)
                .weight(1f),
        ) {
            Text(
                text = onThisDay.newsText,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(10.dp))
            onThisDay.pages[0].description?.let {
                Text( // description text
                    text = it,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Composable
fun OnThisDayNewsItem(modifier: Modifier = Modifier) {

}

@Composable
fun TodayFeaturedArticle(
    modifier: Modifier = Modifier,
    tfa: Tfa
) {
    val typography = MaterialTheme.typography

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        val imageModifier = Modifier
            .heightIn(min = 180.dp)
            .fillMaxWidth()
            .clip(shape = MaterialTheme.shapes.large)

        AsyncImage(
            model = tfa.thumbnail.imgUrl,
            error = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = imageModifier,
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = tfa.normalizedTitle,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            modifier = Modifier.padding(bottom = 8.dp),
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = tfa.description,
            style = typography.labelLarge,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(text = tfa.timestamp, style = typography.bodySmall)

    }
}

@Composable
fun FeedListNewsSection(
    modifier: Modifier = Modifier,
    news: List<News>
) {
    Column {
        Text(
            modifier = modifier.padding(16.dp),
            text = stringResource(id = R.string.today_hot_topic),
            style = MaterialTheme.typography.titleLarge
        )
        Row(
            modifier = Modifier
                .horizontalScroll(rememberScrollState())
                .height(IntrinsicSize.Max)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (newsitem in news) {
                NewsArticlesFeed(news = newsitem.newsArticles[0], modifier = modifier)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        NewsItemDivider()
    }
}

@Composable
fun NewsItemDivider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 14.dp),
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.07f)
    )
}

@Composable
fun NewsArticlesFeed(modifier: Modifier = Modifier, news: NewsArticles) {

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = modifier.width(280.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth(),
                error = painterResource(id = R.drawable.ic_launcher_background),
                contentScale = ContentScale.Crop,
                model = news.thumbnail?.imgUrl,
                contentDescription = null
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = news.normalizedTitle,
                    style = MaterialTheme.typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = news.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(
    modifier: Modifier = Modifier,
    searchActionClick: () -> Unit = {},
    scrollBehavior: TopAppBarScrollBehavior
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

@Preview(showBackground = true)
@Composable
private fun OnThisDaySectionPreview() {
    OnThisDaySection(
        onThisDay = Onthisday(
            pages = listOf(
                Page(
                    description = "Web Developer are swithcing jobs to android",
                    longDescription = "No long",
                    normalizedTitle = "Turkey leader erdagen was died!",
                    thumbnail = PageThumnail("https://upload.wikimedia.org/wikipedia/commons/5/50/Recep_Tayyip_Erdo%C4%9Fan_2018_%28cropped%29.jpg"),
                    "121232",
                    "12th September 2034"
                ), Page(
                    description = "Sanjay Somunath was died",
                    longDescription = "No long",
                    normalizedTitle = "Turkey leader erdagen was died!",
                    thumbnail = PageThumnail("https://upload.wikimedia.org/wikipedia/commons/5/50/Recep_Tayyip_Erdo%C4%9Fan_2018_%28cropped%29.jpg"),
                    "121232",
                    "12th September 2034"
                ), Page(
                    description = "Web Developer are swithcing jobs to android",
                    longDescription = "No long",
                    normalizedTitle = "Turkey leader erdagen was died!",
                    thumbnail = PageThumnail("https://upload.wikimedia.org/wikipedia/commons/5/50/Recep_Tayyip_Erdo%C4%9Fan_2018_%28cropped%29.jpg"),
                    "121232",
                    "12th September 2034"
                ), Page(
                    description = "Web Developer are swithcing jobs to android",
                    longDescription = "No long",
                    normalizedTitle = "Turkey leader erdagen was died!",
                    thumbnail = PageThumnail("https://upload.wikimedia.org/wikipedia/commons/5/50/Recep_Tayyip_Erdo%C4%9Fan_2018_%28cropped%29.jpg"),
                    "121232",
                    "12th September 2034"
                ), Page(
                    description = "Web Developer are swithcing jobs to android",
                    longDescription = "No long",
                    normalizedTitle = "Turkey leader erdagen was died!",
                    thumbnail = PageThumnail("https://upload.wikimedia.org/wikipedia/commons/5/50/Recep_Tayyip_Erdo%C4%9Fan_2018_%28cropped%29.jpg"),
                    "121232",
                    "12th September 2034"
                ), Page(
                    description = "Web Developer are swithcing jobs to android",
                    longDescription = "No long",
                    normalizedTitle = "Turkey leader erdagen was died!",
                    thumbnail = PageThumnail("https://upload.wikimedia.org/wikipedia/commons/5/50/Recep_Tayyip_Erdo%C4%9Fan_2018_%28cropped%29.jpg"),
                    "121232",
                    "12th September 2034"
                ), Page(
                    description = "Web Developer are swithcing jobs to android",
                    longDescription = "No long",
                    normalizedTitle = "Turkey leader erdagen was died!",
                    thumbnail = PageThumnail("https://upload.wikimedia.org/wikipedia/commons/5/50/Recep_Tayyip_Erdo%C4%9Fan_2018_%28cropped%29.jpg"),
                    "121232",
                    "12th September 2034"
                ), Page(
                    description = "Web Developer are swithcing jobs to android",
                    longDescription = "No long",
                    normalizedTitle = "Turkey leader erdagen was died!",
                    thumbnail = PageThumnail("https://upload.wikimedia.org/wikipedia/commons/5/50/Recep_Tayyip_Erdo%C4%9Fan_2018_%28cropped%29.jpg"),
                    "121232",
                    "12th September 2034"
                ), Page(
                    description = "Web Developer are swithcing jobs to android",
                    longDescription = "No long",
                    normalizedTitle = "Turkey leader erdagen was died!",
                    thumbnail = PageThumnail("https://upload.wikimedia.org/wikipedia/commons/5/50/Recep_Tayyip_Erdo%C4%9Fan_2018_%28cropped%29.jpg"),
                    "121232",
                    "12th September 2034"
                )
            ), newsText = "Turkey President Eldogen", yearAtHappend = 2023
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun FeedListArticleSection() {
    FeedListNewsSection(
        news = listOf(
            News(
                story = "Dev story",
                newsArticles = listOf(
                    NewsArticles(
                        contentUrls = NewsContentUrl(Mobile("")),
                        description = "Mobile Devices start to get damages",
                        longDescription = "",
                        normalizedTitle = "Turkey leader erdagen was died!",
                        thumbnail = NewsArticleThumnail(""),
                        tid = "232",
                        timestamp = "wwe",
                        title = "Android dev"
                    ),NewsArticles(
                        contentUrls = NewsContentUrl(Mobile("")),
                        description = "Mobile Devices start to get damages",
                        longDescription = "",
                        normalizedTitle = "Turkey leader erdagen was died!",
                        thumbnail = NewsArticleThumnail(""),
                        tid = "232",
                        timestamp = "wwe",
                        title = "Android dev"
                    ),NewsArticles(
                        contentUrls = NewsContentUrl(Mobile("")),
                        description = "Mobile Devices start to get damages",
                        longDescription = "",
                        normalizedTitle = "Turkey leader erdagen was died!",
                        thumbnail = NewsArticleThumnail(""),
                        tid = "232",
                        timestamp = "wwe",
                        title = "Android dev"
                    ))
            )
        )
    )
}

@Preview
@Composable
private fun NewsArticleFeedPreview() {
    NewsArticlesFeed(news =
        NewsArticles(
            contentUrls = NewsContentUrl(Mobile("")),
            description = "Mobile Devices start to get damages",
            longDescription = "",
            normalizedTitle = "Turkey leader erdagen was died!",
            thumbnail = NewsArticleThumnail(""),
            tid = "232",
            timestamp = "wwe",
            title = "Android dev"
        ))
}