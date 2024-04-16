package com.nuncamaria.films.loader

import com.nuncamaria.films.ui.FilmsActivity
import com.nuncamaria.navigation.DestinationViewRoute
import com.nuncamaria.navigation.Navigation
import com.nuncamaria.navigation.viewloaderpath.NavigationRouteLoader
import com.nuncamaria.navigation.viewtype.ActivityViewType

class FilmsModuleEntryPointLoader : NavigationRouteLoader {
    init {
        Navigation.addRouteView(
            DestinationViewRoute.FILMS.routeId,
            ActivityViewType(FilmsActivity::class.java)
        )
    }
}