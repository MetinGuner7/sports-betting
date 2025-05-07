package com.sports.auth.login.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.navOptions
import com.sports.common.base.Route
import kotlinx.serialization.Serializable

private const val DEEPLINK_KEY = "Login"

@Serializable data object Login : Route(DEEPLINK_KEY)

fun NavController.navigateToLogin(navOptions: NavOptions? = null) {
    this.navigate(Login, navOptions)
}

fun NavController.navigateToLoginPopUpTo(
    navOptions: NavOptions? = null,
) {
    this.navigateToLogin(
        navOptions { popUpTo(this@navigateToLoginPopUpTo.graph.id) { inclusive = true } },
    )
}

fun NavGraphBuilder.login(navigateRegister: () -> Unit, navigateToHome: () -> Unit) {
    composable<Login> { LoginRoute(navigateRegister = navigateRegister, navigateToHome = navigateToHome) }
}
