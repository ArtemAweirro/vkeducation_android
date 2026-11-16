package com.practicum.vkeducation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.practicum.vkeducation.presentation.appdetails.AppDetailsScreen
import com.practicum.vkeducation.presentation.applist.AppListScreen

@Composable
fun RootNavHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ListAppDestination
    ) {
        composable<ListAppDestination> {
            AppListScreen(
                onClickApp = {
                    navController.navigate(AppDetailsDestination)
                }
            )
        }
        composable<AppDetailsDestination> {
            AppDetailsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onShowcaseAppClick = {
                    navController.navigate(ListAppDestination)
                }
            )
        }
    }
}
