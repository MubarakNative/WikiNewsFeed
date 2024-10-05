package com.mubarak.wikinews.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.mubarak.wikinews.R
import kotlinx.serialization.Serializable

/*
enum class TopLevelDestination(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    FEATURED(R.string.featured, Icons.Default.Star, R.string.featured),
    BREAKING(R.string.breaking, Icons.Default.Person, R.string.breaking),
    BOOKMARKS(R.string.bookmarks, Icons.Default.Bookmarks, R.string.bookmarks),
    SETTINGS(R.string.settings, Icons.Default.Settings, R.string.settings),
}
*/

@Serializable
data object Featured

@Serializable
data object Breaking

@Serializable
data object Bookmarks

@Serializable
data object Settings

@Serializable
data object Search

data class TopLevelRoute<T : Any>(val route: T,@StringRes val label:Int, val icon: ImageVector)

val TopLevelDestination = listOf(
    TopLevelRoute(route = Featured, label = R.string.featured, Icons.Default.Star),
    TopLevelRoute(route = Breaking,label = R.string.breaking, Icons.Default.BarChart),
    TopLevelRoute(route = Bookmarks, label = R.string.bookmarks,Icons.Default.Bookmarks),
    TopLevelRoute(route = Settings,label = R.string.settings, Icons.Default.Settings),
)