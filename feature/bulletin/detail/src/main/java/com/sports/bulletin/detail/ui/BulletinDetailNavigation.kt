package com.sports.bulletin.detail.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sports.common.base.Route
import kotlinx.serialization.Serializable

private const val DEEPLINK_KEY = "BulletinDetail"

@Serializable data class BulletinDetail(val key:String) : Route(DEEPLINK_KEY)

fun NavController.navigateToBulletinDetail(
    navOptions: NavOptions? = null,
    key: String
) {
    this.navigate(BulletinDetail(key = key), navOptions)
}

fun NavGraphBuilder.bulletinDetail(
    navigateBack: () -> Unit,
) {
    composable<BulletinDetail>() {
        BulletinDetailRoute(
            navigateBack = navigateBack,
        )
    }
}
