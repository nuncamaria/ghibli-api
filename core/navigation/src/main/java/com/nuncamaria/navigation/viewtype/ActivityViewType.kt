package com.nuncamaria.navigation.viewtype

import android.app.Activity

data class ActivityViewType(
    val activity: Class<out Activity>
) : NavigationViewType