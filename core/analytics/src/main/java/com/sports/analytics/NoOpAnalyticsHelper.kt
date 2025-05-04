package com.sports.analytics

import android.os.Bundle
import com.google.firebase.analytics.ParametersBuilder

class NoOpAnalyticsHelper : AnalyticsHelper {
    override fun logEvent(event: AnalyticsEvent) = Unit

    override fun logEventWithBundle(eventType: String, bundle: Bundle) = Unit

    override fun logEventWithParamBuilder(
        eventType: String,
        builder: ParametersBuilder.() -> Unit,
    ) = Unit

}
