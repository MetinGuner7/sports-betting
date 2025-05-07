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
import com.sports.bulletin.list.ui.navigateToBulletinListPopUpTo
import com.sports.common.base.Route
import com.sports.auth.login.ui.login
import com.sports.auth.login.ui.navigateToLogin
import com.sports.auth.login.ui.navigateToLoginPopUpTo
import com.sports.auth.register.ui.navigateToRegister
import com.sports.auth.register.ui.register
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
            navigateToLogin = navController::navigateToLoginPopUpTo,
            navigateToHome = navController::navigateToBulletinListPopUpTo,
        )
        login(
            navigateToHome = navController::navigateToBulletinListPopUpTo,
            navigateRegister = navController::navigateToRegister
        )
        register(
            navigateToHome = navController::navigateToBulletinListPopUpTo,
            navigateLogin = navController::navigateToLogin,
            navigateBack = navController::navigateUp
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
