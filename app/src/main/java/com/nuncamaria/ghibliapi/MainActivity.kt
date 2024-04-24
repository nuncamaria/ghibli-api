package com.nuncamaria.ghibliapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nuncamaria.ghibliapi.ui.GhibliApp
import com.nuncamaria.ghibliapi.ui.MainViewModel
import com.nuncamaria.navigation.DestinationViewRoute
import com.nuncamaria.navigation.Navigation
import com.nuncamaria.navigation.appnavhostconfig.rememberAppState
import com.nuncamaria.ui.splash.splashViewConfig
import com.nuncamaria.ui.theme.GhibliAPITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition { viewModel.isSplashVisible.value }
            splashViewConfig()
        }

        setContent {
            val appState = rememberAppState()

            GhibliAPITheme {
                GhibliApp(appState) {

                    NavHost(
                        navController = appState.navController,
                        startDestination = DestinationViewRoute.FILMS.routeId
                    ) {
                        composable(route = DestinationViewRoute.FILMS.routeId) {
                            Navigation.addComposableView(to = DestinationViewRoute.FILMS.routeId)
                        }

                        composable(route = DestinationViewRoute.LOCATIONS.routeId) {
                            Navigation.addComposableView(to = DestinationViewRoute.LOCATIONS.routeId)
                        }

                        composable(route = DestinationViewRoute.PEOPLE.routeId) {
                            Navigation.addComposableView(to = DestinationViewRoute.PEOPLE.routeId)
                        }
                    }
                }
            }
        }
    }
}