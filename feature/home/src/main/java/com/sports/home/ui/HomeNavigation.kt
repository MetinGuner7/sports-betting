package com.sports.home.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sports.common.base.Route
import kotlinx.serialization.Serializable

private const val DEEPLINK_KEY = "DASHBOARD"

@Serializable data class Home(val storyId: String? = null) : Route(DEEPLINK_KEY)

fun NavController.navigateToHome(navOptions: NavOptions? = null, storyId: String? = null) {
    this.navigate(Home(storyId = storyId), navOptions)
}

fun NavController.navigateToHomePopUpTo(navOptions: NavOptions? = null) {
    this.navigateToHome(
        androidx.navigation.navOptions {
            popUpTo(this@navigateToHomePopUpTo.graph.id) { inclusive = true }
        }
    )
}

fun NavGraphBuilder.home(
    navigateToLogin: () -> Unit,
) {
    composable<Home>() {
        HomeRoute(
            navigateToLogin = navigateToLogin
        )
    }
}
