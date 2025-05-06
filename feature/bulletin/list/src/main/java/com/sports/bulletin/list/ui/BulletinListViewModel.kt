package com.sports.bulletin.list.ui

import androidx.lifecycle.viewModelScope
import com.sports.analytics.AnalyticsHelper
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import com.sports.component.domain.usecase.GetSportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class BulletinListViewModel
@Inject
constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getSportsUseCase: GetSportsUseCase
    ) : BaseViewModel<BulletinListViewState>(){

    private var searchJob: Job? = null

    override fun createInitialState(): BulletinListViewState = BulletinListViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    fun onHandleViewEvent(viewEvent: BulletinListEvent) {
        when (viewEvent) {
            BulletinListEvent.NavigateBack -> sendEventInViewModelScope(viewEvent)
            BulletinListEvent.NavigateToLogin -> sendEventInViewModelScope(viewEvent)
            BulletinListEvent.OnCloseIcon -> {
                searchJob?.cancel()
                setState {
                    copy(
                        searchQuery = "",
                        filteredSports = currentState.allSports
                    )
                }
            }
            is BulletinListEvent.OnKeyChanged -> onKeyChanged(viewEvent.value)
        }
    }

    init {
        getSports()
    }

    private fun getSports() {
        executeUseCase(
            useCase = getSportsUseCase,
            parameter = Unit,
            onSuccess = {
                setState { copy(
                    allSports = it.toPersistentList(),
                    filteredSports = it.toPersistentList(),
                ) }
            },
            onError = {
                setState { copy(error = it?.message) }
            },
        )
    }

    private fun onKeyChanged(value: String){
        setState { copy(searchQuery = value) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300L)
            filterSports(value)
        }
    }

    private fun filterSports(query: String) {
        val filteredList = if (query.isBlank()) {
            currentState.allSports
        } else {
            currentState.allSports.filter { sport ->
                sport.title.contains(query, ignoreCase = true) ||
                        sport.group.contains(query, ignoreCase = true) ||
                        sport.description?.contains(query, ignoreCase = true) == true
            }.toPersistentList()
        }
        setState { copy(filteredSports = filteredList) }
    }

    override fun onCleared() {
        super.onCleared()
        searchJob?.cancel()
    }
}

sealed interface BulletinListEvent : BaseEvent {
    data object NavigateBack : BulletinListEvent

    data object NavigateToLogin : BulletinListEvent

    data class OnKeyChanged(val value: String) : BulletinListEvent

    data object OnCloseIcon : BulletinListEvent
}
