package com.sports.auth.register.ui

import com.sports.analytics.AnalyticsEvent
import com.sports.analytics.AnalyticsHelper
import com.sports.auth.component.domain.usecase.RegisterUseCase
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val analyticsHelper: AnalyticsHelper
) : BaseViewModel<RegisterViewState>() {

    override fun createInitialState(): RegisterViewState = RegisterViewState()

    override fun showLoading(isLoading: Boolean) {
        setState { copy(loading = isLoading) }
    }

    fun onHandleViewEvent(viewEvent: RegisterEvent) {
        when (viewEvent) {
            is RegisterEvent.OnEmailChanged -> setState {
                copy(
                    email = viewEvent.email,
                    registrationError = null
                )
            }

            is RegisterEvent.OnPasswordChanged -> setState {
                copy(
                    password = viewEvent.password,
                    registrationError = null
                )
            }

            is RegisterEvent.OnConfirmPasswordChanged -> setState {
                copy(
                    confirmPassword = viewEvent.confirmPassword,
                    registrationError = null
                )
            }

            RegisterEvent.OnRegisterButtonClicked -> performRegistration()
            RegisterEvent.NavigateToLogin -> sendEventInViewModelScope(viewEvent)
            RegisterEvent.NavigateToHome -> sendEventInViewModelScope(viewEvent)
            RegisterEvent.NavigateBack -> sendEventInViewModelScope(viewEvent)
        }
    }

    private fun performRegistration() {
        val email = currentState.email.trim()
        val password = currentState.password
        val confirmPassword = currentState.confirmPassword

        if (email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
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
        if (password != confirmPassword) {
            setState { copy(registrationError = "Şifreler eşleşmiyor.") }
            return
        }

        executeUseCase(
            useCase = registerUseCase,
            parameter = RegisterUseCase.Params(email = email, password = password),
            onSuccess = { authUser ->
                analyticsHelper.logEvent(AnalyticsEvent(type = "registration_success"))
                sendEventInViewModelScope(RegisterEvent.NavigateToHome)
            },
            onError = { throwable ->
                setState {
                    copy(
                        registrationError = throwable?.message ?: "Kayıt sırasında bir hata oluştu."
                    )
                }
            }
        )
    }
}

sealed interface RegisterEvent : BaseEvent {
    data class OnEmailChanged(val email: String) : RegisterEvent
    data class OnPasswordChanged(val password: String) : RegisterEvent
    data class OnConfirmPasswordChanged(val confirmPassword: String) : RegisterEvent
    data object OnRegisterButtonClicked : RegisterEvent
    data object NavigateToLogin : RegisterEvent
    data object NavigateToHome : RegisterEvent
    data object NavigateBack : RegisterEvent
}