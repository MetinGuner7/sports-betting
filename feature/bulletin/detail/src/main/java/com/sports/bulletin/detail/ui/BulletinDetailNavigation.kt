package com.sports.bulletin.detail.ui

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.sports.common.base.CustomNavTypes
import com.sports.common.base.Route
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.ui.BulletinNavType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

private const val DEEPLINK_KEY = "BulletinDetail"

@Serializable data class BulletinDetail(val sport: EventDetailDomainModel) : Route(DEEPLINK_KEY)

fun NavController.navigateToBulletinDetail(
    navOptions: NavOptions? = null,
    key: EventDetailDomainModel
) {
    this.navigate(BulletinDetail(sport = key), navOptions)
}

fun NavGraphBuilder.bulletinDetail(
    navigateBack: () -> Unit,
) {
    composable<BulletinDetail>(
        typeMap = mapOf(typeOf<EventDetailDomainModel>() to CustomNavTypes.BulletinNavType)
    ) {
        BulletinDetailRoute(
            navigateBack = navigateBack,
        )
    }
}
