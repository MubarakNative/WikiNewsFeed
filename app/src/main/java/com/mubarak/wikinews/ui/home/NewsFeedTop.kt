package com.mubarak.wikinews.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.tfa.Tfa
import com.mubarak.wikinews.utils.TimeStampConvertor

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

        NewsFeedImage(
            modifier = imageModifier,
            imgUrl = tfa.thumbnail.imgUrl
        )

        Spacer(modifier = Modifier.height(16.dp))

        NewsTitle(
            modifier = Modifier.padding(bottom = 8.dp),
            text = tfa.normalizedTitle
        )

        Text(
            text = tfa.largeDescription,
            style = typography.labelLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Text(
            text = TimeStampConvertor.formatTimestampToUtc(tfa.timestamp),
            style = typography.bodySmall
        )

    }
}