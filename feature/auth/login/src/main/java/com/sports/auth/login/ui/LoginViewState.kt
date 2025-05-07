package com.sports.auth.login.ui

import androidx.compose.runtime.Stable
import com.sports.common.base.IViewState

@Stable
data class LoginViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val email: String = "",
    val password: String = "",
    val isRememberMe: Boolean = false,
    val enabledLoginButton: Boolean = true,
    val registrationError: String? = null,
) : IViewState
