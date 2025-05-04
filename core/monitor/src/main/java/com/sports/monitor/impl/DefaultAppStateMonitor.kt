package com.sports.monitor.impl

import com.sports.common.model.DialogType
import com.sports.common.model.FriendlyMessageDTO
import com.sports.monitor.AppEvent
import com.sports.monitor.AppEventType
import com.sports.monitor.AppStateMonitor
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultAppStateMonitor @Inject constructor() : AppStateMonitor {
    private val _events = Channel<AppEvent>(Channel.BUFFERED)
    override val events: Flow<AppEvent> = _events.receiveAsFlow()

    /**
     * Emits a new event to the channel. This is a suspending function that will suspend if the
     * channel's buffer is full.
     *
     * @param event The [AppEvent] to be emitted
     */
    override suspend fun emitEvent(event: AppEvent) {
        _events.send(event)
    }

    override suspend fun showDialog(
        message: String,
        title: String?,
        cancelable: Boolean,
        positiveButtonText: String?,
        negativeButtonText: String?,
        neutralButtonText: String?,
        onPositiveClick: (() -> Unit)?,
        onNegativeClick: (() -> Unit)?,
        onNeutralClick: (() -> Unit)?,
        dialogType: DialogType?,
    ) {
        emitEvent(
            AppEvent.ShowMessage(
                friendlyMessage =
                    FriendlyMessageDTO(
                        title = title,
                        message = message,
                        cancelable = cancelable,
                        buttonPositive = positiveButtonText,
                        buttonNegative = negativeButtonText,
                        buttonNeutral = neutralButtonText,
                        positiveButtonClick = onPositiveClick,
                        negativeButtonClick = onNegativeClick,
                    ),
                appEventType = AppEventType.MODAL,
                dialogType = dialogType,
            )
        )
    }
}
