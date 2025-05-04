package com.sports.splash.ui

import androidx.lifecycle.viewModelScope
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel
@Inject
constructor(

) : BaseViewModel<SplashViewState>() {

    override fun createInitialState(): SplashViewState = SplashViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    init {
        viewModelScope.launch {
            delay(3000)
            sendEventInViewModelScope(SplashEvent.NavigateToLogin)

        }
    }

    private fun checkToken() {
/*        executeUseCase(
            useCase = checkTokenUseCase,
            parameter = Unit,
            onSuccess = { token ->
                if (token.isEmpty()) {
                    sendEventInViewModelScope(SplashEvent.NavigateToLogin(false))
                } else {
                    viewModelScope.launch { getProfile() }
                }
            },
            onError = { sendEventInViewModelScope(SplashEvent.NavigateToLogin(false)) },
        )*/
    }


}

sealed interface SplashEvent : BaseEvent {
    data object NavigateToHome : SplashEvent

    data object NavigateToLogin : SplashEvent
}
