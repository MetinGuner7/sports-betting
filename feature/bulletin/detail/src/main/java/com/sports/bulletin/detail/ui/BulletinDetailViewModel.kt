package com.sports.bulletin.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sports.analytics.AnalyticsHelper
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import com.sports.common.base.CustomNavTypes
import com.sports.component.data.model.EventOddsRequest
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.usecase.GetEventDetailsUseCase
import com.sports.component.domain.usecase.GetSportsUseCase
import com.sports.component.ui.BulletinNavType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf


@HiltViewModel
class BulletinDetailViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
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
        val sport = savedStateHandle
            .toRoute<BulletinDetail>(
                typeMap = mapOf(typeOf<EventDetailDomainModel>() to CustomNavTypes.BulletinNavType)
            )
        setState { copy(eventDetail = sport.sport) }
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
