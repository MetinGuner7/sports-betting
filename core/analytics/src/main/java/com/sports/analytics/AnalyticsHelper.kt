package com.sports.analytics

import android.os.Bundle
import com.google.firebase.analytics.ParametersBuilder

interface AnalyticsHelper {
    fun logEvent(event: AnalyticsEvent)

    fun logEventWithBundle(eventType: String, bundle: Bundle)

    fun logEventWithParamBuilder(eventType: String, builder: ParametersBuilder.() -> Unit)
}
