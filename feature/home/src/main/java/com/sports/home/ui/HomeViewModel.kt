package com.sports.home.ui

import com.sports.analytics.AnalyticsHelper
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,

    ) : BaseViewModel<HomeViewState>(){

    override fun createInitialState(): HomeViewState = HomeViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    fun onHandleViewEvent(viewEvent: HomeEvent) {

    }
}

sealed interface HomeEvent : BaseEvent {
    data object NavigateBack : HomeEvent
    data object NavigateToLogin : HomeEvent
}