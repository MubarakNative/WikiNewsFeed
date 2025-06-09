package com.mubarak.wikinews.ui

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mubarak.wikinews.ui.breaking.MostReadRoute
import com.mubarak.wikinews.ui.home.HomeRoute
import com.mubarak.wikinews.ui.search.SearchRoute

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
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
                navController.navigateWithBackStack(Search)
            })
        }

        composable<MostRead> {
            MostReadRoute(onSearchActionClick = {
                navController.navigateWithBackStack(Search)
            })
        }

        composable<Search> {
            SearchRoute(onBackClick = { navController.popBackStack() })
        }
    }
}