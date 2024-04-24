package com.nuncamaria.navigation.appnavhostconfig

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.ui.util.trace
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

@Composable
fun rememberAppState(navController: NavHostController = rememberNavController()): GhibliAppState {
    return remember(navController) {
        GhibliAppState(navController = navController)
    }
}

@Stable
class GhibliAppState(val navController: NavHostController) {
    val topLevelDestinations: List<TopLevelDestination> = listOf(Films, Locations)
    var currentTopLevelDestination: String = Films.route

    val shouldShowBottomBar: Boolean
        get() = when (currentTopLevelDestination) {
            Films.route -> true
            Locations.route -> true
            else -> false
        }

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        trace("Navigation: ${topLevelDestination.route}") {
            val topLevelNavOptions = navOptions {
                popUpTo(navController.graph.findStartDestination().id) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }

            when (topLevelDestination) {
                Films -> navController.navigate(Films.route, topLevelNavOptions)
                Locations -> navController.navigate(Locations.route, topLevelNavOptions)
                else -> {}
            }
        }
    }
}