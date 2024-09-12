package com.mubarak.wikinews.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mubarak.wikinews.data.sources.remote.dto.newsfeed.onthisday.Onthisday
import com.mubarak.wikinews.utils.TimeStampConvertor


@Composable
fun OnThisDaySection(
    modifier: Modifier = Modifier,
    onThisDay: Onthisday
) {
    Row(
        modifier = Modifier, verticalAlignment = Alignment.CenterVertically
    ) { // TODO: provide accessibility service to consider this as a whole item


        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(10.dp)
                .weight(1f),
        ) {

            NewsTitle(
                modifier = Modifier.padding(end = 16.dp),
                text = onThisDay.newsText
            )

            Spacer(modifier = Modifier.height(10.dp))
            onThisDay.pages[0].description?.let {
                Text(
                    // description text
                    text = it, style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(end = 16.dp),
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = TimeStampConvertor.formatTimestampToUtc(onThisDay.pages[0].timestamp),
                style = MaterialTheme.typography.bodySmall
            )
        }

        NewsFeedImage(
            modifier = Modifier
                .padding(16.dp)
                .size(80.dp, 80.dp)
                .clip(MaterialTheme.shapes.small),
            imgUrl = onThisDay.pages[0].thumbnail?.imgUrl // all pages list have same sort of news
        )
    }
}