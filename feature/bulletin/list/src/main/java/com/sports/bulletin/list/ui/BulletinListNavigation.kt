package com.sports.bulletin.list.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sports.common.base.Route
import com.sports.component.domain.model.EventDetailDomainModel
import kotlinx.serialization.Serializable

private const val DEEPLINK_KEY = "BulletinList"

@Serializable data object BulletinList : Route(DEEPLINK_KEY)

fun NavController.navigateToBulletinList(navOptions: NavOptions? = null) {
    this.navigate(BulletinList, navOptions)
}

fun NavController.navigateToBulletinListPopUpTo(navOptions: NavOptions? = null) {
    this.navigateToBulletinList(
        androidx.navigation.navOptions {
            popUpTo(this@navigateToBulletinListPopUpTo.graph.id) { inclusive = true }
        }
    )
}

fun NavGraphBuilder.bulletinList(
    navigateToLogin: () -> Unit,
    navigateToBulletinDetail: (EventDetailDomainModel) -> Unit,
) {
    composable<BulletinList>() {
        BulletinListRoute(
            navigateToLogin = navigateToLogin,
            navigateToBulletinDetail = navigateToBulletinDetail
        )
    }
}
