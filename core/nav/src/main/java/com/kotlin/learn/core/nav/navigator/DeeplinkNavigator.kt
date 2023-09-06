package com.kotlin.learn.core.nav.navigator

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.kotlin.learn.core.common.base.BaseNavigator
import com.kotlin.learn.core.common.util.JsonUtil
import com.kotlin.learn.core.nav.deeplink.DeeplinkNavigation
import com.kotlin.learn.core.nav.NavControllerBinder
import com.kotlin.learn.core.nav.navigator.DeeplinkNavigator.UriConstants.AUTHORITY
import com.kotlin.learn.core.nav.navigator.DeeplinkNavigator.UriConstants.PATH_NOT_FOUND
import com.kotlin.learn.core.nav.navigator.DeeplinkNavigator.UriConstants.SCHEME
import com.kotlin.learn.core.nav.utils.StateHelper
import javax.inject.Inject

class DeeplinkNavigator @Inject constructor(
    val navBinder: NavControllerBinder,
    private val jsonUtil: JsonUtil
) : DeeplinkNavigation, BaseNavigator() {

    override var navController: NavController? = navBinder.navController

    override fun navigate(
        state: String, context: Context, vararg arguments: Pair<String, Any>, navOptions: NavOptions?
    ) {
        try {
            val destination = getStateDestination(state, arguments)
            if (destination != null) {
                navBinder.navController?.navigate(
                    StateHelper.mapCustomerStatusScenario(state, destination).build(),
                    navOptions
                )
            } else {
                Toast.makeText(context, PATH_NOT_FOUND, Toast.LENGTH_SHORT).show()
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
            Toast.makeText(context, PATH_NOT_FOUND, Toast.LENGTH_SHORT).show()
        }
    }

    override fun navigate(state: String, vararg arguments: Pair<String, Any>, navOptions: NavOptions?) {
        try {
            val destination = getStateDestination(state, arguments)
            if (destination != null) {
                navBinder.navController?.navigate(
                    StateHelper.mapCustomerStatusScenario(state, destination).build(),
                    navOptions
                )
            }
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }

    private fun getStateDestination(state: String, arguments: Array<out Pair<String, Any>>): Uri.Builder? {
        return onState(state, arguments)
    }

    private fun onState(state: String, arguments: Array<out Pair<String, Any>>) =
        buildUri(state, arguments)

    private fun buildUri(path: String, arguments: Array<out Pair<String, Any>>) = Uri.Builder()
        .scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(path)
        .addArgument(arguments)

    private fun Uri.Builder.addArgument(arguments: Array<out Pair<String, Any>>): Uri.Builder {
        arguments.forEach {
            appendQueryParameter(
                it.first,
                if (it.second is String) it.second as String
                else jsonUtil.toJson(it.second)
            )
        }
        return this
    }

    object UriConstants {
        const val SCHEME = "bmri.id"
        const val AUTHORITY = "nbds"
        const val PATH_NOT_FOUND = "Path Not Found"
    }
}