package com.nuncamaria.ghibliapi.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import com.nuncamaria.navigation.appnavhostconfig.GhibliAppState
import com.nuncamaria.ui.theme.Colors
import com.nuncamaria.ui.theme.gradient.backgroundRadialGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GhibliApp(appState: GhibliAppState, content: @Composable () -> Unit) {
    val scrollBehaviorPinned = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .background(backgroundRadialGradient)
            .fillMaxSize()
            .nestedScroll(scrollBehaviorPinned.nestedScrollConnection),
        containerColor = Colors.transparent,
        topBar = {
            TopAppBar(
                title = { Text(text = "Ghibli World") },
                colors = TopAppBarColors(
                    containerColor = Color.Unspecified,
                    scrolledContainerColor = Color.White,
                    navigationIconContentColor = Colors.neutral100,
                    titleContentColor = Colors.neutral100,
                    actionIconContentColor = Colors.neutral100
                ),
                scrollBehavior = scrollBehaviorPinned
            )
        },
        bottomBar = {
            var selectedItem by remember { mutableStateOf(appState.currentTopLevelDestination) }
            val items = appState.topLevelDestinations

            if (appState.shouldShowBottomBar) {
                NavigationBar(containerColor = Color.White) {
                    items.forEach { item ->
                        NavigationBarItem(
                            icon = { Icon(item.icon, contentDescription = item.label) },
                            label = { Text(item.label) },
                            selected = selectedItem == item.route,
                            onClick = {
                                selectedItem = item.route
                                appState.navigateToTopLevelDestination(item)
                            }
                        )
                    }
                }
            }
        },
    ) {
        Box(
            modifier = Modifier.padding(it),
            contentAlignment = Alignment.Center
        ) {
            content()
        }
    }
}
