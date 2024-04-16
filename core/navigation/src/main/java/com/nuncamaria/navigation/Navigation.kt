package com.nuncamaria.navigation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import com.nuncamaria.navigation.viewtype.ActivityViewType
import com.nuncamaria.navigation.viewtype.ComposableViewType
import com.nuncamaria.navigation.viewtype.NavigationViewType

object Navigation {

    private val viewRoutes: HashMap<String, NavigationViewType> = hashMapOf()

    fun addRouteView(id: String, routeView: NavigationViewType) {
        viewRoutes[id] = routeView
    }

    fun removeEntryPoint(id: String) {
        viewRoutes.remove(id)
    }

    /**
     * Params:
     * @ctx:Context - Context of Activity
     * @to:String - Destination ID of navigation
     * @infoBundle: Bundle to send info between Activities
     * @registerForActivityResult: Callback Launcher get the result of an Activity
     *
     * @return: the result of navigation,
     * - true for the success navigation,
     * - false for the failure navigation
     */
    fun navigateTo(
        ctx: Context,
        to: String,
        infoBundle: Bundle? = null,
        registerForActivityResult: ActivityResultLauncher<Intent>? = null
    ): Boolean =
        when {
            viewRoutes[to] is ActivityViewType -> {
                navigateToActivity(
                    ctx,
                    (viewRoutes[to] as ActivityViewType).activity,
                    infoBundle,
                    registerForActivityResult
                )
                true
            }

            else -> {
                false
            }
        }

    /**
     * Params:
     * @to:String - Destination ID of navigation
     * @params: List of Any data type to be passed to Composable Lambda function
     *
     * @return: the result of navigation,
     * - true for the success navigation,
     * - false for the failure navigation
     */
    @Composable
    fun addComposableView(
        to: String,
        params: List<Any>? = null
    ): Boolean =
        when {
            viewRoutes[to] is ComposableViewType -> {
                GetComposeView(viewRoutes[to] as ComposableViewType, params)
                true
            }

            else -> {
                false
            }
        }

    private fun navigateToActivity(
        ctx: Context,
        to: Class<out Activity>,
        infoBundle: Bundle? = null,
        registerForActivityResult: ActivityResultLauncher<Intent>? = null
    ) {
        val intent = Intent(ctx, to)
        infoBundle?.let {
            intent.putExtras(it)
        }
        registerForActivityResult?.launch(intent) ?: ctx.startActivity(intent)
    }

    @Composable
    private fun GetComposeView(to: ComposableViewType, params: List<Any>?) =
        to.view(params)
}