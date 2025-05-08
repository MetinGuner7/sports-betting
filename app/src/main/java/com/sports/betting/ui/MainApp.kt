package com.sports.betting.ui

import android.content.Intent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.sports.auth.login.ui.Login
import com.sports.auth.register.ui.Register
import com.sports.basket.ui.Basket
import com.sports.basket.ui.navigateToBasket
import com.sports.betting.MainActivityViewEvent
import com.sports.betting.MainActivityViewState
import com.sports.betting.navigation.MainAppNavHost
import com.sports.common.model.DialogType
import com.sports.designsystem.component.AppText
import com.sports.monitor.AppEvent
import com.sports.monitor.AppStateMonitor
import com.sports.monitor.LocalAppStateMonitor
import com.sports.splash.ui.Splash
import com.sports.splash.ui.navigateToSplashPopUpTo
import com.sports.ui.component.AppInfoDialog
import com.sports.ui.extentions.ObserveAsEvents

/**
 *
 * @param mainUiState The state of the main activity.
 * @param modifier The modifier to be applied to the layout.
 * @param appState The state of the app, including navigation state.
 * @param newIntent The new intent that might contain deep link data.
 */
@Composable
fun MainApp(
    uiState: MainActivityViewState,
    modifier: Modifier = Modifier,
    appState: MainAppState = rememberMainAppState(),
    onViewEvent: (MainActivityViewEvent) -> Unit = {},
    appStateMonitor: AppStateMonitor = LocalAppStateMonitor.current,
    newIntent: Intent? = null,
) {

    var isContentDrawn by remember { mutableStateOf(false) }
    val currentOnViewEvent by rememberUpdatedState(onViewEvent)
    val currentDestination = appState.currentDestination

    ObserveAsEvents(appStateMonitor.events) { event ->
        when (event) {
            is AppEvent.UnAuthorized -> {
                if (currentDestination?.route != Splash::class.qualifiedName) {
                    appState.navController.navigateToSplashPopUpTo()
                }
            }

            is AppEvent.ShowMessage -> {
                currentOnViewEvent(
                    MainActivityViewEvent.OnShowErrorDialog(
                        friendlyMessage = event.friendlyMessage,
                        appEventType = event.appEventType,
                        throwable = event.throwable,
                        dialogType = event.dialogType,
                    )
                )
            }

            is AppEvent.HandleDeeplink -> {}
        }
    }
        Scaffold(
            modifier =
                modifier
                    .takeIf { currentDestination?.route != Splash::class.java.name }
                    ?.navigationBarsPadding() ?: modifier,
            contentWindowInsets = WindowInsets(0, 0, 0, 0),
            bottomBar = {},
            floatingActionButton = {
                if (currentDestination?.route != Splash::class.qualifiedName &&
                    currentDestination?.route != Basket::class.qualifiedName &&
                    currentDestination?.route != Login::class.qualifiedName &&
                    currentDestination?.route != Register::class.qualifiedName
                ) {
                    val formattedTotalOdds = "%.2f".format(uiState.totalOdds)
                    ExtendedFloatingActionButton(
                        text = { AppText(text = "Oran: $formattedTotalOdds",
                            style = MaterialTheme.typography.titleSmall.copy(
                                color = MaterialTheme.colorScheme.surface)
                        )},
                        icon = {
                            Icon(Icons.Filled.ShoppingCart, contentDescription = "Kupon",
                                tint = MaterialTheme.colorScheme.surface)
                        },
                        onClick = {
                            appState.navController.navigateToBasket()
                        },
                        containerColor = MaterialTheme.colorScheme.surfaceDim
                    )
                }
            }
        ) { padding ->
            MainAppNavHost(
                modifier = Modifier.fillMaxSize().padding(padding).consumeWindowInsets(padding),
                navController = appState.navController,
            )

        }

    if (uiState.showErrorModal) {
        AppInfoDialog(
            dialogType = uiState.dialogType ?: DialogType.ERROR,
            title = uiState.messageModel?.title.orEmpty(),
            message =
                uiState.messageModel?.message.orEmpty(),
            buttonText = uiState.messageModel?.buttonPositive.orEmpty(),
            negativeButtonText = uiState.messageModel?.buttonNegative,
            onDismissRequest = { onViewEvent(MainActivityViewEvent.OnDismissErrorDialog) },
            onNegativeClick = {
                uiState.messageModel?.negativeButtonClick?.invoke()
                onViewEvent(MainActivityViewEvent.OnDismissErrorDialog)
            },
            onPositiveClick = {
                uiState.messageModel?.positiveButtonClick?.invoke()
                onViewEvent(MainActivityViewEvent.OnDismissErrorDialog)
            },
        )
    }
}
