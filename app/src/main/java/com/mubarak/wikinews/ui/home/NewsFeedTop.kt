package com.mubarak.wikinews.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.onClick
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.mubarak.wikinews.R
import com.mubarak.wikinews.data.network.models.dto.newsfeed.tfa.Tfa
import com.mubarak.wikinews.utils.TimeStampConvertor

@Composable
fun TodayFeaturedArticle(
    modifier: Modifier = Modifier, onClick: () -> Unit, tfa: Tfa
) {
    val typography = MaterialTheme.typography
    val timeStamp = remember {
        TimeStampConvertor.formatTimestampToUtc(tfa.timestamp)
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
            val imageModifier = Modifier
                .heightIn(min = 180.dp, max = 200.dp)
                .fillMaxWidth()
                .clip(shape = MaterialTheme.shapes.large)

            NewsFeedImage(
                modifier = imageModifier, imgUrl = tfa.thumbnail?.imgUrl
            )

            Spacer(modifier = Modifier.height(16.dp))

            NewsTitle(
                text = tfa.largeDescription, modifier = Modifier.padding(bottom = 6.dp)
            )
            Text(
                text = timeStamp, style = typography.bodySmall
            )

        }
    }

}
