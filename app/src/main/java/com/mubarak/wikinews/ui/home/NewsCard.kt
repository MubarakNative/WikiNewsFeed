package com.mubarak.wikinews.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.mubarak.wikinews.R

@Composable
fun NewsFeedImage(
    modifier: Modifier = Modifier,
    imgUrl: String?,
    contentDescription: String? = null
) {
    val context = LocalContext.current
    AsyncImage(
        model = ImageRequest.Builder(context = context).data(imgUrl)
            .crossfade(true).build(),
        error = painterResource(id = R.drawable.news_placeholder),
        placeholder = painterResource(id = R.drawable.loading_placeholder),
        contentDescription = contentDescription,
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
        maxLines = 3,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.W500,
        fontSize = 19.sp,
    )
}

@Composable
fun NewsTitleSection(
    modifier: Modifier = Modifier,
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier.padding(start = 8.dp)
    )
}