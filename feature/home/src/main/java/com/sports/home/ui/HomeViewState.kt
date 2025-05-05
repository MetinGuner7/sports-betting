package com.sports.home.ui

import androidx.compose.runtime.Stable
import com.sports.common.base.IViewState

@Stable
data class HomeViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,

) : IViewState