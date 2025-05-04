package com.sports.analytics

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ParametersBuilder
import com.google.firebase.analytics.logEvent
import javax.inject.Inject

private const val FORTY = 40
private const val HUNDRED = 100

class FirebaseAnalyticsHelper
@Inject
constructor(private val firebaseAnalytics: FirebaseAnalytics) : AnalyticsHelper {

    override fun logEvent(event: AnalyticsEvent) {
        val bundle =
            Bundle().apply {
                for (extra in event.extras) {
                    // Truncate parameter keys and values according to firebase maximum length
                    // values.
                    this.putString(extra.key.take(FORTY), extra.value.take(HUNDRED))
                }
            }
        firebaseAnalytics.logEvent(event.type.take(FORTY), bundle)
    }

    override fun logEventWithBundle(eventType: String, bundle: Bundle) {
        val newBundle =
            Bundle().apply {
                bundle.keySet().forEach {
                    this.putString(it.take(FORTY), bundle.getString(it)?.take(HUNDRED))
                }
            }
        firebaseAnalytics.logEvent(eventType.take(FORTY), newBundle)
    }

    override fun logEventWithParamBuilder(
        eventType: String,
        builder: ParametersBuilder.() -> Unit,
    ) {
        firebaseAnalytics.logEvent(eventType.take(FORTY), builder)
    }
}
