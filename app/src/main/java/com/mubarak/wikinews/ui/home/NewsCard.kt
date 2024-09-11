package com.mubarak.wikinews.ui.home

import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.mubarak.wikinews.R

@Composable
fun NewsFeedImage(
    modifier: Modifier = Modifier,
    imgUrl: String?
) {
    AsyncImage(
        model = imgUrl,
        error = painterResource(id = R.drawable.news_placeholder),
        contentDescription = null,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}

@Composable
fun NewsTitle(
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    text: String
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.W500,
        style = MaterialTheme.typography.titleLarge
    )
}