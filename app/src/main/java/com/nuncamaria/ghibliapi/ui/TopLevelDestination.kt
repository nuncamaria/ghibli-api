package com.nuncamaria.ghibliapi.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.nuncamaria.navigation.DestinationViewRoute

interface TopLevelDestination {
    val id: Int
    val icon: ImageVector
    val label: String
    val route: String
}

object Films : TopLevelDestination {
    override val id = 1
    override val icon = Icons.Outlined.Tv
    override val label = "Films"
    override val route = DestinationViewRoute.FILMS.routeId
}
