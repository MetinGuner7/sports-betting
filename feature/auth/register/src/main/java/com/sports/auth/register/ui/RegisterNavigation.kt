package com.sports.auth.register.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sports.common.base.Route
import kotlinx.serialization.Serializable

private const val DEEPLINK_KEY = "Register"

@Serializable
data object Register : Route(DEEPLINK_KEY)

fun NavController.navigateToRegister(navOptions: NavOptions? = null) {
    this.navigate(Register, navOptions)
}

fun NavGraphBuilder.register(
    navigateBack: () -> Unit,
    navigateLogin: () -> Unit,
    navigateToHome: () -> Unit
) {
    composable<Register> {
        RegisterRoute(
            navigateLogin = navigateLogin,
            navigateToHome = navigateToHome,
            navigateBack = navigateBack
        )
    }
}
