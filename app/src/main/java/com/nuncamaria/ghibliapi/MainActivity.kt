package com.nuncamaria.ghibliapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nuncamaria.ghibliapi.ui.GhibliApp
import com.nuncamaria.ghibliapi.ui.MainViewModel
import com.nuncamaria.ghibliapi.ui.rememberAppState
import com.nuncamaria.ghibliapi.ui.theme.GhibliAPITheme
import com.nuncamaria.navigation.DestinationViewRoute
import com.nuncamaria.navigation.Navigation
import com.nuncamaria.ui.splash.splashViewConfig
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
                    Navigation.addComposableView(to = DestinationViewRoute.FILMS.routeId)
                }
            }
        }
    }
}