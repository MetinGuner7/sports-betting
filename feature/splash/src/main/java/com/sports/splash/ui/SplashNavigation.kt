package com.sports.splash.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sports.common.base.Route
import kotlinx.serialization.Serializable

private const val DEEPLINK_KEY = "Splash"

@Serializable data object Splash : Route(DEEPLINK_KEY)

fun NavController.navigateToSplash(navOptions: NavOptions? = null) {
    this.navigate(Splash, navOptions)
}

fun NavController.navigateToSplashPopUpTo(navOptions: NavOptions? = null) {
    this.navigateToSplash(
        androidx.navigation.navOptions {
            popUpTo(this@navigateToSplashPopUpTo.graph.id) { inclusive = true }
        }
    )
}

fun NavGraphBuilder.splash(
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit,
) {
    composable<Splash> {
        SplashRoute(
            navigateToHome = navigateToHome,
            navigateToLogin = navigateToLogin,
        )
    }
}
