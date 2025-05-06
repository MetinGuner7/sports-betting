package com.sports.betting.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.sports.bulletin.detail.ui.bulletinDetail
import com.sports.bulletin.detail.ui.navigateToBulletinDetail
import com.sports.bulletin.list.ui.bulletinList
import com.sports.bulletin.list.ui.navigateToBulletinList
import com.sports.bulletin.list.ui.navigateToBulletinListPopUpTo
import com.sports.common.base.Route
import com.sports.home.ui.home
import com.sports.home.ui.navigateToHomePopUpTo
import com.sports.login.ui.login
import com.sports.login.ui.navigateToLoginPopUpTo
import com.sports.splash.ui.Splash
import com.sports.splash.ui.splash

@Composable
fun MainAppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: Route = Splash,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
        popEnterTransition = { EnterTransition.None },
        popExitTransition = { ExitTransition.None },
    ) {
        splash(
            navigateToLogin = navController::navigateToBulletinListPopUpTo,
            navigateToHome = {},
        )
        login(
            navigateToHome = navController::navigateToHomePopUpTo,
            navigateRegister = {}
        )
        home(
            navigateToLogin = {}
        )
        bulletinList(
            navigateToLogin = {},
            navigateToBulletinDetail = {
                navController.navigateToBulletinDetail(key = it)
            }
        )
        bulletinDetail(
            navigateBack = navController::navigateUp
        )
    }
}
