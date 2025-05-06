package com.sports.betting.ui

import android.content.Intent
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.sports.betting.MainActivityViewEvent
import com.sports.betting.MainActivityViewState
import com.sports.betting.navigation.MainAppNavHost
import com.sports.betting.navigation.TopLevelDestination
import com.sports.common.model.DialogType
import com.sports.monitor.AppEvent
import com.sports.monitor.AppStateMonitor
import com.sports.monitor.LocalAppStateMonitor
import com.sports.splash.ui.Splash
import com.sports.splash.ui.navigateToSplashPopUpTo
import com.sports.ui.component.AppInfoDialog
import com.sports.ui.extentions.ObserveAsEvents

const val BOTTOM_BAR_DELAY = 50L

/**
 *
 * @param mainUiState The state of the main activity.
 * @param modifier The modifier to be applied to the layout.
 * @param appState The state of the app, including navigation state.
 * @param newIntent The new intent that might contain deep link data.
 */
@OptIn(ExperimentalMaterial3Api::class)
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
    var deeplinkIntent by remember { mutableStateOf(newIntent) }
    val currentOnViewEvent by rememberUpdatedState(onViewEvent)
    val currentDestination = appState.currentDestination
    val activity = LocalActivity.current

    ObserveAsEvents(appStateMonitor.events) { event ->
        when (event) {
            is AppEvent.UnAuthorized -> {
                if (currentDestination?.route != Splash::class.qualifiedName) {
                    appState.navController.navigateToSplashPopUpTo()
                }
            }

            // Handle show message event by displaying an error dialog
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
            bottomBar = {
                if (appState.shouldShowBottomBar && isContentDrawn) {

                }
            },
        ) { padding ->
            // Set up the navigation host for the app

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

fun NavDestination?.isTopLevelDestinationInHierarchy(
    destination: TopLevelDestination,
    appState: MainAppState,
) =
    this?.hierarchy?.any {
        it.route?.contains(".${destination.route.javaClass.simpleName}", false) ?: false
    } ?: false
