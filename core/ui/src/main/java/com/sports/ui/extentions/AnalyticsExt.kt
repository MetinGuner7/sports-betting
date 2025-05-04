package com.sports.ui.extentions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import com.sports.analytics.AnalyticsEvent
import com.sports.analytics.AnalyticsHelper
import com.sports.analytics.util.LocalAnalyticsHelper

/** Classes and functions associated with analytics events for the UI. */
fun AnalyticsHelper.logScreenView(screenName: String) {
    logEvent(
        AnalyticsEvent(
            type = AnalyticsEvent.Types.SCREEN_VIEW,
            extras = listOf(AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.SCREEN_NAME, screenName)),
        )
    )
}

fun AnalyticsHelper.buttonClick(screenName: String, buttonId: String) {
    logEvent(
        AnalyticsEvent(
            type = AnalyticsEvent.Types.BUTTON_CLICK,
            extras =
                listOf(
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.SCREEN_NAME, screenName),
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.BUTTON_ID, buttonId),
                ),
        )
    )
}

/** A side-effect which records a screen view event. */
@Composable
fun TrackScreenViewEvent(
    screenName: String,
    analyticsHelper: AnalyticsHelper = LocalAnalyticsHelper.current,
) =
    DisposableEffect(Unit) {
        analyticsHelper.logScreenView(screenName)
        onDispose {}
    }
