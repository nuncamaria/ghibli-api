package com.nuncamaria.ghibliapi.ui

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GhibliApp(appState: GhibliAppState, content: @Composable () -> Unit) {
    val scrollBehaviorPinned = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehaviorPinned.nestedScrollConnection),
        topBar = {
            TopAppBar(
                title = { Text(text = "Ghibli World") },
                scrollBehavior = scrollBehaviorPinned
            )
        },
        bottomBar = {
            var selectedItem by remember { mutableStateOf(Films.route) }
            val items = appState.topLevelDestinations

            appState.currentTopLevelDestination = selectedItem

            if (appState.shouldShowBottomBar) {
                NavigationBar(containerColor = Color.Unspecified) {
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
