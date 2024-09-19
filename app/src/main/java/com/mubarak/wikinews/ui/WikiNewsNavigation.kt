package com.mubarak.wikinews.ui

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import kotlinx.serialization.Serializable

class WikiNewsNavigation(navController: NavHostController){
    val navigateToSearch:() -> Unit = {
        navController.navigate(Search){
            launchSingleTop = true
            restoreState = true
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
        }
    }
}