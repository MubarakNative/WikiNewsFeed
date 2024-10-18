package com.mubarak.wikinews.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mubarak.wikinews.R
import com.mubarak.wikinews.data.sources.remote.dto.ContentUrls
import com.mubarak.wikinews.data.sources.remote.dto.Mobile
import com.mubarak.wikinews.data.sources.remote.dto.NewsArticles
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.news.NewsArticleThumnail
import com.mubarak.wikinews.utils.TimeStampConvertor

@Composable
fun NewsArticlesFeed(
    modifier: Modifier = Modifier, onClick: () -> Unit, news: NewsArticles
) {

    val timeStamp: String = remember(news.timestamp) {
        TimeStampConvertor.formatTimestampToUtc(news.timestamp)
    }
    val clickActionLabel = stringResource(id = R.string.open_article_detail)
    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier
            .width(280.dp)
            .semantics {
                onClick(label = clickActionLabel, action = null)
            },
        onClick = onClick
    ) {
        Column {

            NewsFeedImage(
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth(),
                imgUrl = news.thumbnail?.imgUrl,
                contentDescription = news.normalizedTitle
            )

            Column(modifier = Modifier.padding(16.dp)) {
                NewsTitle(
                    text = news.description ?: news.normalizedTitle,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = news.longDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = timeStamp, style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}

val fakeNewsArticles: NewsArticles = NewsArticles(
    contentUrls = ContentUrls(Mobile("")),
    description = "Ktor makes http request way more easier than before.",
    longDescription = "",
    normalizedTitle = "Ktor as a Http Client for android",
    thumbnail = NewsArticleThumnail(""),
    tid = "03-344-454",
    timestamp = "September 8",
    pageId = 1,
    title = "Ktor make developer life easier by providing the power of using kotlin intuitive api for Http request"
)

@Preview
@Composable
private fun NewsArticlePreview() {
    NewsArticlesFeed(news = fakeNewsArticles, onClick = {})
}