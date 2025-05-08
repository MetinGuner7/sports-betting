package com.sports.basket.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sports.common.base.Route
import kotlinx.serialization.Serializable

private const val DEEPLINK_KEY = "Basket"

@Serializable data object Basket : Route(DEEPLINK_KEY)

fun NavController.navigateToBasket(navOptions: NavOptions? = null) {
    this.navigate(Basket, navOptions)
}


fun NavGraphBuilder.basket(
    navigateBack: () -> Unit,
) {
    composable<Basket>() {
        BasketRoute(
            navigateBack = navigateBack,
        )
    }
}
