package com.sports.analytics

import android.os.Bundle
import android.util.Log
import com.google.firebase.analytics.ParametersBuilder
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

private const val TAG = "StubAnalyticsHelper"

/**
 * An implementation of AnalyticsHelper just writes the events to logcat. Used in builds where no
 * analytics events should be sent to a backend.
 */
@Singleton
internal class StubAnalyticsHelper @Inject constructor() : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) {
        Timber.d(TAG, "Received analytics event: $event")
    }

    override fun logEventWithBundle(eventType: String, bundle: Bundle) {
        Timber.d(TAG, "Received analytics event with Bundle: $eventType , bundle: $bundle")
    }

    override fun logEventWithParamBuilder(
        eventType: String,
        builder: ParametersBuilder.() -> Unit,
    ) {
        Timber.d(TAG, "Received analytics event with ParamBuilder: $eventType")
    }

}
