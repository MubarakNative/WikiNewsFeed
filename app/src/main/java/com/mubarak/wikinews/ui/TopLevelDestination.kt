package com.mubarak.wikinews.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.mubarak.wikinews.R

enum class TopLevelDestination(
    @StringRes val label: Int,
    val icon: ImageVector,
    @StringRes val contentDescription: Int
) {
    HOME(R.string.home, Icons.Default.Home, R.string.home),
    BREAKING(R.string.breaking, Icons.Default.Person, R.string.breaking),
    BOOKMARKS(R.string.bookmarks, Icons.Default.Bookmarks, R.string.bookmarks),
    SETTINGS(R.string.settings, Icons.Default.Settings, R.string.settings),
}