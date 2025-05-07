package com.sports.auth.register.ui

import com.sports.common.base.IViewState

data class RegisterViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val registrationError: String? = null,
) : IViewState