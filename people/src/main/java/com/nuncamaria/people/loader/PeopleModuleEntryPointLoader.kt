package com.nuncamaria.people.loader

import com.nuncamaria.people.ui.PeopleView
import com.nuncamaria.navigation.DestinationViewRoute
import com.nuncamaria.navigation.Navigation
import com.nuncamaria.navigation.viewloaderpath.NavigationRouteLoader
import com.nuncamaria.navigation.viewtype.ComposableViewType

class PeopleModuleEntryPointLoader : NavigationRouteLoader {
    init {
        Navigation.addRouteView(
            DestinationViewRoute.PEOPLE.routeId,
            ComposableViewType { PeopleView() }
        )
    }
}