package com.mubarak.wikinews.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.mubarak.wikinews.ui.home.HomeScreen

@Composable
fun WikiNewsApp(
    modifier: Modifier = Modifier
) {

    var currentDestination by rememberSaveable { mutableStateOf(TopLevelDestination.HOME) }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            TopLevelDestination.entries.forEach {
                item(icon = {
                    Icon(
                        it.icon, contentDescription = stringResource(it.contentDescription)
                    )
                },
                    label = { Text(stringResource(it.label)) },
                    selected = it == currentDestination,
                    onClick = { currentDestination = it })
            }
        }, navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = Color.Transparent,
        )
    ) {
        when (currentDestination) {
            TopLevelDestination.HOME -> {
                HomeScreen()
            }

            TopLevelDestination.BREAKING -> {
            }

            TopLevelDestination.BOOKMARKS -> {
            }

            TopLevelDestination.SETTINGS -> {
            }
        }
    }
}
