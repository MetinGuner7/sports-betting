package com.sports.monitor

import com.sports.common.model.DialogType
import kotlinx.coroutines.flow.Flow

interface AppStateMonitor {
    val events: Flow<AppEvent>

    suspend fun emitEvent(event: AppEvent)

    suspend fun showDialog(
        message: String,
        title: String? = null,
        positiveButtonText: String? = null,
        negativeButtonText: String? = null,
        onPositiveClick: (() -> Unit)? = null,
        onNegativeClick: (() -> Unit)? = null,
        onNeutralClick: (() -> Unit)? = null,
        dialogType: DialogType? = null,
    )
}
