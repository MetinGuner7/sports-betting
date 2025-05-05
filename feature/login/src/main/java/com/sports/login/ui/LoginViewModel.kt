package com.sports.login.ui

import androidx.lifecycle.viewModelScope
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(

) : BaseViewModel<LoginViewState>() {

    init {

    }

    override fun createInitialState(): LoginViewState = LoginViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    fun onHandleViewEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            is LoginEvent.OnUsernameChanged -> setState { copy(username = viewEvent.text) }
            is LoginEvent.OnPasswordChanged -> setState { copy(password = viewEvent.text) }
            is LoginEvent.OnRememberMeChanged -> {
                viewModelScope.launch {
                    setState { copy(isRememberMe = viewEvent.isChecked) }
                }
            }
            is LoginEvent.OnButtonClicked -> {
                verifyLogin()
            }
            is LoginEvent.NavigateRegister -> {
                sendEventInViewModelScope(viewEvent)
            }
            is LoginEvent.NavigateToHome -> {
                sendEventInViewModelScope(viewEvent)
            }
        }
    }


    private fun verifyLogin() {
        /*executeUseCase(
            useCase = loginUseCase,
            parameter =
                LoginUseCaseParams(
                    username = currentState.username,
                    password = currentState.password,
                    rememberMe = currentState.isRememberMe,
                ),
            onSuccess = { data ->

            },
            onError = { error -> },
        )*/
        sendEventInViewModelScope(LoginEvent.NavigateToHome)
    }
}

sealed interface LoginEvent : BaseEvent {
    data class OnUsernameChanged(val text: String) : LoginEvent

    data class OnPasswordChanged(val text: String) : LoginEvent

    data class OnRememberMeChanged(val isChecked: Boolean) : LoginEvent

    data object OnButtonClicked : LoginEvent

    data object NavigateRegister : LoginEvent

    data object NavigateToHome : LoginEvent
}
