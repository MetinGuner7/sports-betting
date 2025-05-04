package com.sports.betting

import androidx.compose.runtime.Stable
import com.sports.common.base.BaseViewModel
import com.sports.common.base.IViewState
import com.sports.common.model.DialogType
import com.sports.common.model.FriendlyMessageDTO
import com.sports.monitor.AppEventType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(

) : BaseViewModel<MainActivityViewState>() {
    override fun createInitialState(): MainActivityViewState = MainActivityViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    fun onHandleViewEvent(viewEvent: MainActivityViewEvent) {
        when (viewEvent) {
            is MainActivityViewEvent.OnShowErrorDialog ->
                handleErrorDialog(
                    friendlyMessage = viewEvent.friendlyMessage,
                    appEventType = viewEvent.appEventType,
                    throwable = viewEvent.throwable,
                    dialogType = viewEvent.dialogType,
                )

            MainActivityViewEvent.OnDismissErrorDialog -> onDismissErrorDialog()
        }
    }

    /**
     * Handles the display of error dialogs based on the provided `AppEventType`.
     *
     * @param friendlyMessage The user-friendly message to be displayed in the dialog.
     * @param appEventType The type of UI component to use for displaying the message.
     * @param throwable The throwable representing the error, if any.
     */
    private fun handleErrorDialog(
        friendlyMessage: FriendlyMessageDTO?,
        appEventType: AppEventType,
        throwable: Throwable?,
        dialogType: DialogType?,
    ) {
        when (appEventType) {
            AppEventType.MODAL -> {
                if (throwable != null) {

                } else {
                    setState {
                        copy(
                            loading = false,
                            showErrorModal = true,
                            messageModel = friendlyMessage,
                            dialogType = dialogType,
                        )
                    }
                }
            }
            AppEventType.TOAST -> {}
            AppEventType.NONE -> {}
        }
    }

    private fun onDismissErrorDialog() {
        setState { copy(showErrorModal = false, messageModel = null, showToastMessage = false) }
    }

}

sealed interface MainActivityViewEvent {
    data object OnDismissErrorDialog : MainActivityViewEvent

    data class OnShowErrorDialog(
        val dialogType: DialogType? = null,
        val friendlyMessage: FriendlyMessageDTO?,
        val appEventType: AppEventType = AppEventType.MODAL,
        val throwable: Throwable? = null,
    ) : MainActivityViewEvent
}

@Stable
data class MainActivityViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val messageModel: FriendlyMessageDTO? = null,
    val showToastMessage: Boolean = false,
    val dialogType: DialogType? = null,
    val token: String? = null,
) : IViewState
