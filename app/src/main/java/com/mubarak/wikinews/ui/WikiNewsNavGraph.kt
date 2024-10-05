package com.mubarak.wikinews.ui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mubarak.wikinews.ui.bookmarks.BookmarksScreen
import com.mubarak.wikinews.ui.breaking.BreakingNewsScreen
import com.mubarak.wikinews.ui.home.HomeScreen
import com.mubarak.wikinews.ui.search.SearchScreen

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
            HomeScreen(onSearchActionClick = {
                navController.navigate(Search)
            //   navAction.navigateToSearch
            })
        }

        composable<Breaking> {
            BreakingNewsScreen()
        }

        composable<Bookmarks>{
            BookmarksScreen()
        }

        composable<Settings> {
            SettingsScreen()
        }

        composable<Search> {
            SearchScreen()
        }
    }
}