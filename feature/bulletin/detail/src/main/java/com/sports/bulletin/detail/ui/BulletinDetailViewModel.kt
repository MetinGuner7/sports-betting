package com.sports.bulletin.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sports.analytics.AnalyticsHelper
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import com.sports.component.data.model.EventOddsRequest
import com.sports.component.domain.usecase.GetEventDetailsUseCase
import com.sports.component.domain.usecase.GetSportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BulletinDetailViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getEventDetailsUseCase: GetEventDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<BulletinDetailViewState>(){

    override fun createInitialState(): BulletinDetailViewState = BulletinDetailViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    fun onHandleViewEvent(viewEvent: BulletinDetailEvent) {
        when (viewEvent) {
            BulletinDetailEvent.NavigateBack -> sendEventInViewModelScope(viewEvent)
            is BulletinDetailEvent.AddBetToBasket -> TODO()
            BulletinDetailEvent.RetryLoad -> TODO()
        }
    }

    init {
        val key = savedStateHandle.toRoute<BulletinDetail>().key
        getEventDetails(key)
    }

    private fun getEventDetails(key: String) {
        executeUseCase(
            useCase = getEventDetailsUseCase,
            parameter = EventOddsRequest(key = key),
            onSuccess = {
                setState { copy(eventDetails = it.toPersistentList()) }
            },
            onError = {
                setState { copy(error = it?.message) }
            },
        )
    }
}

sealed interface BulletinDetailEvent : BaseEvent {
    data object NavigateBack : BulletinDetailEvent

    data class AddBetToBasket(
        val eventId: String,
        val marketKey: String,
        val outcomeName: String,
        val price: Double
    ) : BulletinDetailEvent
    data object RetryLoad : BulletinDetailEvent
}
