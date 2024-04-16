package com.nuncamaria.navigation.viewtype

import androidx.compose.runtime.Composable

data class ComposableViewType(
    val view: @Composable (params: List<Any>?) -> Unit
) : NavigationViewType