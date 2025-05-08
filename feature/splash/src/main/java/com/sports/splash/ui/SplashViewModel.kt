package com.sports.splash.ui

import androidx.lifecycle.viewModelScope
import com.sports.auth.component.domain.usecase.GetCurrentUserUseCase
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
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel<SplashViewState>() {

    override fun createInitialState(): SplashViewState = SplashViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    init {
        viewModelScope.launch {
            delay(3000)
            checkToken()

        }
    }

    private fun checkToken() {
        executeUseCase(
            useCase = getCurrentUserUseCase,
            parameter = Unit,
            onSuccess = { isLogin ->
                if (isLogin) {
                    sendEventInViewModelScope(SplashEvent.NavigateToHome)
                } else {
                    sendEventInViewModelScope(SplashEvent.NavigateToHome)
                }
            },
            onError = { sendEventInViewModelScope(SplashEvent.NavigateToHome) },
        )
    }


}

sealed interface SplashEvent : BaseEvent {
    data object NavigateToHome : SplashEvent

    data object NavigateToLogin : SplashEvent
}
