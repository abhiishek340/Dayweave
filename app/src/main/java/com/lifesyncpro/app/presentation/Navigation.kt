package com.lifesyncpro.app.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lifesyncpro.app.presentation.ui.screens.HomeScreen
import com.lifesyncpro.app.presentation.ui.screens.ProfileScreen
import com.lifesyncpro.app.presentation.ui.screens.TaskScreen
import com.lifesyncpro.app.presentation.ui.screens.HealthScreen
import com.lifesyncpro.app.presentation.ui.screens.InsightsScreen

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Task : Screen("task")
    object Health : Screen("health")
    object Insights : Screen("insights")
}

@Composable
fun LifeSyncProNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController)
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(Screen.Task.route) {
            TaskScreen(navController)
        }
        composable(Screen.Health.route) {
            HealthScreen(navController)
        }
        composable(Screen.Insights.route) {
            InsightsScreen(navController)
        }
    }
} 