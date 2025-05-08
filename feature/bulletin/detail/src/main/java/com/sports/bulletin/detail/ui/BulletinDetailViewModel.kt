package com.sports.bulletin.detail.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.sports.analytics.AnalyticsEvent
import com.sports.analytics.AnalyticsHelper
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import com.sports.common.base.CustomNavTypes
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.ui.BulletinNavType
import com.sports.datastore.model.BasketItem
import com.sports.datastore.usecase.AddBetToBasketUseCase
import com.sports.datastore.usecase.GetBasketItemsUseCase
import com.sports.datastore.usecase.RemoveBetFromBasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.typeOf


@HiltViewModel
class BulletinDetailViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    savedStateHandle: SavedStateHandle,
    private val addBetToBasketUseCase: AddBetToBasketUseCase,
    private val getBasketItemsUseCase: GetBasketItemsUseCase,
    private val removeBetFromBasketUseCase: RemoveBetFromBasketUseCase,
) : BaseViewModel<BulletinDetailViewState>(){

    override fun createInitialState(): BulletinDetailViewState = BulletinDetailViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    fun onHandleViewEvent(viewEvent: BulletinDetailEvent) {
        when (viewEvent) {
            BulletinDetailEvent.NavigateBack -> sendEventInViewModelScope(viewEvent)
            is BulletinDetailEvent.AddBetToBasket -> addBetToBasket(
                newItem = viewEvent.basketItem,
            )
            BulletinDetailEvent.RetryLoad -> TODO()
        }
    }

    init {
        val sport = savedStateHandle
            .toRoute<BulletinDetail>(
                typeMap = mapOf(typeOf<EventDetailDomainModel>() to CustomNavTypes.BulletinNavType)
            ).sport
        setState { copy(eventDetail = sport) }
        observeBasketItems()
        val analyticsParams = listOf(
            AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.EVENT_ID, sport.id),
            AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.HOME_TEAM, sport.homeTeam),
            AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.AWAY_TEAM, sport.awayTeam)
        )
        analyticsHelper.logEvent(
            AnalyticsEvent(
                type = AnalyticsEvent.Types.MATCH_DETAIL,
                extras = analyticsParams
            )
        )
    }

    private fun observeBasketItems() {
        viewModelScope.launch {
            getBasketItemsUseCase(Unit)
                .onEach { items ->
                    setState { copy(basketItems = items.toPersistentList()) }
                }
                .catch {}
                .launchIn(viewModelScope)
        }
    }

    private fun addBetToBasket(newItem: BasketItem) {
        viewModelScope.launch {
            val currentBasket = currentState.basketItems
            val exactSameItemInBasket = currentBasket.find { it.selectionId == newItem.selectionId }

            if (exactSameItemInBasket != null) {
                removeItemFromBasketInternal(exactSameItemInBasket.selectionId)
            } else {
                val conflictingItemInSameMarket = currentBasket.find { existingItem ->
                    existingItem.eventId == newItem.eventId &&
                            existingItem.marketKey == newItem.marketKey &&
                            existingItem.selectionId != newItem.selectionId
                }

                if (conflictingItemInSameMarket != null) {
                    executeUseCase(
                        isShowLoading = false,
                        useCase = removeBetFromBasketUseCase,
                        parameter = conflictingItemInSameMarket.selectionId,
                        onSuccess = {
                            actuallyAddItemToBasketInternal(newItem)
                        },
                        onError = {
                        }
                    )
                } else {
                    actuallyAddItemToBasketInternal(newItem)
                }
            }
        }
    }

    private fun actuallyAddItemToBasketInternal(item: BasketItem) {
        executeUseCase(
            isShowLoading = false,
            useCase = addBetToBasketUseCase,
            parameter = AddBetToBasketUseCase.Params(
                eventId = item.eventId,
                marketKey = item.marketKey,
                name = item.outcomeName,
                price = item.outcomePrice,
                homeTeam = item.homeTeam,
                awayTeam = item.awayTeam,
                selectionId = item.selectionId,
                bookmakerKey = item.bookmakerKey,
            ),
            onSuccess = {
                val analyticsParams = listOf(
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.EVENT_ID, item.eventId),
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.BOOKMAKER_KEY, item.bookmakerKey),
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.MARKET_KEY, item.marketKey),
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.OUTCOME_NAME, item.outcomeName),
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.OUTCOME_PRICE, item.outcomePrice.toString()),
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.SELECTION_ID, item.selectionId),
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.HOME_TEAM, item.homeTeam),
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.AWAY_TEAM, item.awayTeam)
                )
                analyticsHelper.logEvent(
                    AnalyticsEvent(
                        type = AnalyticsEvent.Types.ADD_TO_BASKET,
                        extras = analyticsParams
                    )
                )
            },
            onError = {}
        )
    }

    private fun removeItemFromBasketInternal(selectionId: String) {
        executeUseCase(
            isShowLoading = false,
            useCase = removeBetFromBasketUseCase,
            parameter = selectionId,
            onSuccess = {},
            onError = {}
        )
    }
}

sealed interface BulletinDetailEvent : BaseEvent {
    data object NavigateBack : BulletinDetailEvent

    data class AddBetToBasket(val basketItem: BasketItem) : BulletinDetailEvent
    data object RetryLoad : BulletinDetailEvent
}
