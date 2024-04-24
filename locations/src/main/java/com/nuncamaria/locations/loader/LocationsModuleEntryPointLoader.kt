package com.nuncamaria.locations.loader

import com.nuncamaria.locations.ui.LocationsView
import com.nuncamaria.navigation.DestinationViewRoute
import com.nuncamaria.navigation.Navigation
import com.nuncamaria.navigation.viewloaderpath.NavigationRouteLoader
import com.nuncamaria.navigation.viewtype.ComposableViewType

class LocationsModuleEntryPointLoader : NavigationRouteLoader {
    init {
        Navigation.addRouteView(
            DestinationViewRoute.LOCATIONS.routeId,
            ComposableViewType { LocationsView() }
        )
    }
}