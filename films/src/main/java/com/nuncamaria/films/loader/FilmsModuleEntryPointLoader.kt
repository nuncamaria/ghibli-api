package com.nuncamaria.films.loader

import com.nuncamaria.films.ui.FilmsView
import com.nuncamaria.navigation.DestinationViewRoute
import com.nuncamaria.navigation.Navigation
import com.nuncamaria.navigation.viewloaderpath.NavigationRouteLoader
import com.nuncamaria.navigation.viewtype.ComposableViewType

class FilmsModuleEntryPointLoader : NavigationRouteLoader {
    init {
        Navigation.addRouteView(
            DestinationViewRoute.FILMS.routeId,
            ComposableViewType { FilmsView() }
        )
    }
}