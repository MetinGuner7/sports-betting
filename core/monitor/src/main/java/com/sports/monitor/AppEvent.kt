package com.sports.monitor

import androidx.compose.runtime.staticCompositionLocalOf
import com.sports.common.model.DialogType
import com.sports.common.model.FriendlyMessageDTO

/**
 * Represents application-wide events that need to be handled by the UI layer. This sealed interface
 * provides a type-safe way to handle different types of events such as authentication failures and
 * error messages.
 */
sealed interface AppEvent {
    /**
     * Event triggered when user authentication fails or session expires. This typically requires
     * navigating the user back to the login screen.
     */
    data object UnAuthorized : AppEvent

    /**
     * Event for showing error messages to the user. Supports multiple presentation types (modal,
     * toast) with customizable styling.
     *
     * @property friendlyMessage The user-friendly message to be displayed
     * @property appEventType The type of UI component to use for displaying the message (default:
     *   MODAL)
     */
    data class ShowMessage(
        val dialogType: DialogType? = null,
        val friendlyMessage: FriendlyMessageDTO?,
        val appEventType: AppEventType = AppEventType.MODAL,
        val throwable: Throwable? = null,
    ) : AppEvent

    data class HandleDeeplink(val deeplinkString: String? = null) : AppEvent
}

/** Defines the different types of UI components that can be used to display events. */
enum class AppEventType {
    NONE,
    MODAL,
    TOAST,
}

val LocalAppStateMonitor =
    staticCompositionLocalOf<AppStateMonitor> { error("No AppStateMonitor provided") }
