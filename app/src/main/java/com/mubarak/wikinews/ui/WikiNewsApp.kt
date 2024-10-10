package com.mubarak.wikinews.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun WikiNewsApp(
    navController: NavHostController = rememberNavController()
) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            TopLevelDestination.forEach { topLevelRoute ->
                item(selected = currentDestination?.hierarchy?.any {
                    it.hasRoute(route = topLevelRoute.route::class)
                } == true,
                    label = { Text(text = stringResource(id = topLevelRoute.label))},
                    icon = {
                    Icon(
                        imageVector = topLevelRoute.icon,
                        contentDescription = topLevelRoute.route::class.simpleName
                    )
                },
                    onClick = { navController.navigate(route = topLevelRoute.route) })
            }
        }, navigationSuiteColors = NavigationSuiteDefaults.colors(
            navigationBarContainerColor = Color.Transparent,
        )
    ) {
        WikiNewsNavGraph(navController = navController)
    }
}
