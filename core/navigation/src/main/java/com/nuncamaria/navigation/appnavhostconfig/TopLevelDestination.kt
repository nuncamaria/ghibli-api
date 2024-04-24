package com.nuncamaria.navigation.appnavhostconfig

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationCity
import androidx.compose.material.icons.outlined.People
import androidx.compose.material.icons.outlined.Person
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

object Locations : TopLevelDestination {
    override val id = 2
    override val icon = Icons.Outlined.LocationCity
    override val label = "Locations"
    override val route = DestinationViewRoute.LOCATIONS.routeId
}


object People : TopLevelDestination {
    override val id = 3
    override val icon = Icons.Outlined.People
    override val label = "People"
    override val route = DestinationViewRoute.PEOPLE.routeId
}
