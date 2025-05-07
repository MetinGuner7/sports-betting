package com.sports.auth.login.ui

import androidx.lifecycle.viewModelScope
import com.sports.auth.component.data.model.LoginRequest
import com.sports.auth.component.domain.usecase.LoginUseCase
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject
constructor(
    private val loginUseCase: LoginUseCase,
) : BaseViewModel<LoginViewState>() {

    override fun createInitialState(): LoginViewState = LoginViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    fun onHandleViewEvent(viewEvent: LoginEvent) {
        when (viewEvent) {
            is LoginEvent.OnUsernameChanged -> setState { copy(email = viewEvent.text) }
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
        val email = currentState.email.trim()
        val password = currentState.password

        if (email.isBlank() || password.isBlank()) {
            setState { copy(registrationError = "Tüm alanlar doldurulmalıdır.") }
            return
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            setState { copy(registrationError = "Geçerli bir e-posta adresi giriniz.") }
            return
        }
        if (password.length < 6) {
            setState { copy(registrationError = "Şifre en az 6 karakter olmalıdır.") }
            return
        }

        executeUseCase(
            useCase = loginUseCase,
            parameter =
                LoginRequest(
                    email = currentState.email,
                    password = currentState.password,
                ),
            onSuccess = { data ->
                sendEventInViewModelScope(LoginEvent.NavigateToHome)
            },
            onError = { error -> },
        )

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
