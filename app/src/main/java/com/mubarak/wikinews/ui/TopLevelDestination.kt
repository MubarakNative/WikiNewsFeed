package com.mubarak.wikinews.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.mubarak.wikinews.R
import kotlinx.serialization.Serializable


@Serializable
data object Featured

@Serializable
data object MostRead

@Serializable
data object Search

data class TopLevelRoute<T : Any>(val route: T,@StringRes val label:Int, val icon: ImageVector)

val TopLevelDestination = listOf(
    TopLevelRoute(route = Featured, label = R.string.featured, Icons.Default.Star),
    TopLevelRoute(route = MostRead,label = R.string.breaking, Icons.Default.BarChart),
)