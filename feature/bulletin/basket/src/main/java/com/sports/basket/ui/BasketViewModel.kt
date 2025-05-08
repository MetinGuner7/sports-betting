package com.sports.basket.ui

import androidx.lifecycle.viewModelScope
import com.sports.analytics.AnalyticsEvent
import com.sports.analytics.AnalyticsHelper
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import com.sports.datastore.usecase.GetBasketItemsUseCase
import com.sports.datastore.usecase.RemoveBetFromBasketUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getBasketItemsUseCase: GetBasketItemsUseCase,
    private val removeBasketItemUseCase: RemoveBetFromBasketUseCase,
) : BaseViewModel<BasketViewState>() {

    override fun createInitialState(): BasketViewState = BasketViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    fun onHandleViewEvent(viewEvent: BasketEvent) {
        when (viewEvent) {
            is BasketEvent.RemoveItem -> removeItemFromBasket(viewEvent.selectionId)
            else -> sendEventInViewModelScope(viewEvent)
        }
    }

    init {
        fetchBasketItems()
    }

    private fun fetchBasketItems() {
        viewModelScope.launch {
            getBasketItemsUseCase(Unit)
                .onStart {
                    if (currentState.basketItems.isEmpty()) {
                        setState { copy(loading = true) }
                    }
                }
                .catch {
                    setState { copy(loading = false) }
                }
                .collect { items ->
                    val calculatedTotalOdds = if (items.isEmpty()) {
                        0.0
                    } else {
                        items.map { it.outcomePrice }.reduce { acc, price -> acc * price }
                    }
                    setState {
                        copy(
                            loading = false,
                            basketItems = items.toPersistentList(),
                            totalOdds = calculatedTotalOdds,
                        )
                    }
                }
        }
    }

    private fun removeItemFromBasket(itemId: String) {
        executeUseCase(
            isShowLoading = false,
            useCase = removeBasketItemUseCase,
            parameter = itemId,
            onSuccess = {
                val analyticsParams = listOf(
                    AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.SELECTION_ID, itemId),
                )
                analyticsHelper.logEvent(
                    AnalyticsEvent(
                        type = AnalyticsEvent.Types.REMOVE_FROM_BASKET,
                        extras = analyticsParams
                    )
                )
                fetchBasketItems()
            },
            onError = {}
        )
    }
}


sealed interface BasketEvent : BaseEvent {
    data object NavigateBack : BasketEvent
    data class RemoveItem(val selectionId: String) : BasketEvent
}
