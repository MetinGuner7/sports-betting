package com.sports.splash.ui

import androidx.compose.runtime.Stable
import com.sports.common.base.IViewState

@Stable
data class SplashViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val isUserLoggedIn: Boolean = false,
) : IViewState