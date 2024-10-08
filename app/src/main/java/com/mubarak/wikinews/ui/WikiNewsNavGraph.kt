package com.mubarak.wikinews.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mubarak.wikinews.ui.bookmarks.BookmarksRoute
import com.mubarak.wikinews.ui.breaking.BreakingNewsRoute
import com.mubarak.wikinews.ui.home.HomeRoute
import com.mubarak.wikinews.ui.search.SearchRoute

@Composable
fun WikiNewsNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Featured
    ){
        composable<Featured> {
            HomeRoute(onSearchActionClick = {
                navController.navigate(Search)
            })
        }

        composable<Breaking> {
            BreakingNewsRoute()
        }

        composable<Bookmarks>{
            BookmarksRoute()
        }

        composable<Settings> {
            SettingsRoute()
        }

        composable<Search> {
            SearchRoute()
        }
    }
}