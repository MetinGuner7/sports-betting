package com.sports.bulletin.list.ui

import androidx.lifecycle.viewModelScope
import com.sports.analytics.AnalyticsHelper
import com.sports.common.base.BaseEvent
import com.sports.common.base.BaseViewModel
import com.sports.component.data.model.EventOddsRequest
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.usecase.GetEventDetailsUseCase
import com.sports.component.domain.usecase.GetSportsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BulletinListViewModel @Inject constructor(
    private val analyticsHelper: AnalyticsHelper,
    private val getSportsUseCase: GetSportsUseCase,
    private val getEventDetailsUseCase: GetEventDetailsUseCase
) : BaseViewModel<BulletinListViewState>() {

    override fun createInitialState(): BulletinListViewState = BulletinListViewState()

    override fun showLoading(isLoading: Boolean) = setState { copy(loading = isLoading) }

    private var fetchEventsJob: Job? = null
    private var searchEventsJob: Job? = null

    init {
        loadSportsList()
    }

    fun onHandleViewEvent(viewEvent: BulletinListEvent) {
        when (viewEvent) {
            is BulletinListEvent.OnSportSelected -> {
                selectSportAndLoadEvents(viewEvent.sportKey)
            }
            is BulletinListEvent.NavigateToEventDetail -> {
                // analyticsHelper.logEvent(AnalyticsEvent(type = AnalyticsEvent.Types.VIEW_ITEM, extras = listOf(AnalyticsEvent.Param(AnalyticsEvent.ParamKeys.ITEM_ID, viewEvent.eventId))))
                sendEventInViewModelScope(viewEvent)
            }
            is BulletinListEvent.OnSearchQueryChanged -> {
                handleSearchQueryChange(viewEvent.query)
            }
            BulletinListEvent.OnSearchClearClicked -> {
                handleSearchQueryChange("")
            }
            BulletinListEvent.RetryLoadSports -> loadSportsList()
            BulletinListEvent.RetryLoadEvents -> {
                currentState.selectedSportKey?.let { key ->
                    loadEventsForSport(key)
                }
            }
            else -> sendEventInViewModelScope(viewEvent)
        }
    }

    private fun loadSportsList() {
        setState { copy(isLoadingSports = true, sportsListError = null) }
        executeUseCase(
            useCase = getSportsUseCase,
            parameter = Unit,
            onSuccess = { sports ->
                val newSportsList = sports.toPersistentList()
                setState { copy(sportsList = newSportsList, isLoadingSports = false) }
                if (newSportsList.isNotEmpty() && currentState.selectedSportKey == null) {
                    selectSportAndLoadEvents(newSportsList.first().key)
                } else if (newSportsList.isEmpty()) {
                    setState { copy(isLoadingEvents = false, eventsForSelectedSport = persistentListOf()) }
                }
            },
            onError = { throwable ->
                setState { copy(sportsListError = throwable?.message ?: "Sporlar yüklenemedi", isLoadingSports = false) }
            }
        )
    }

    private fun selectSportAndLoadEvents(sportKey: String) {
        setState { copy(selectedSportKey = sportKey, eventsError = null, searchQuery = "") }
        loadEventsForSport(sportKey)
    }

    private fun loadEventsForSport(sportKey: String) {
        fetchEventsJob?.cancel()
        fetchEventsJob = viewModelScope.launch {
            setState { copy(isLoadingEvents = true, eventsError = null) }
            val requestParams = EventOddsRequest(key = sportKey)

            executeUseCase(
                isShowLoading = false,
                useCase = getEventDetailsUseCase,
                parameter = requestParams,
                onSuccess = { events ->
                    setState {
                        copy(
                            eventsForSelectedSport = events.toPersistentList(),
                            filteredEventsForSelectedSport = events.toPersistentList(),
                            isLoadingEvents = false
                        )
                    }
                },
                onError = { throwable ->
                    setState {
                        copy(
                            isLoadingEvents = false,
                            eventsForSelectedSport = persistentListOf(),
                            filteredEventsForSelectedSport = persistentListOf(),
                        )
                    }
                }
            )
        }
    }

    private fun handleSearchQueryChange(query: String) {
        setState { copy(searchQuery = query) }
        searchEventsJob?.cancel()
        searchEventsJob = viewModelScope.launch {
            delay(300L)
            filterEvents(query)
        }
    }

    private fun filterEvents(query: String) {
        if (query.isBlank()) {
            setState { copy(filteredEventsForSelectedSport = currentState.eventsForSelectedSport) }
        } else {
            val originalList = currentState.eventsForSelectedSport
            val filtered = originalList.filter { event ->
                event.homeTeam.contains(query, ignoreCase = true) ||
                        event.awayTeam.contains(query, ignoreCase = true) ||
                        event.sportTitle.contains(query, ignoreCase = true)
            }.toPersistentList()
            setState { copy(filteredEventsForSelectedSport = filtered) }
        }
    }
}


sealed interface BulletinListEvent : BaseEvent {
    data object NavigateBack : BulletinListEvent
    data object NavigateToLogin : BulletinListEvent
    data class OnSportSelected(val sportKey: String) : BulletinListEvent
    data class NavigateToEventDetail(val sport: EventDetailDomainModel) : BulletinListEvent
    data class OnSearchQueryChanged(val query: String) : BulletinListEvent
    data object OnSearchClearClicked : BulletinListEvent
    data object RetryLoadSports : BulletinListEvent
    data object RetryLoadEvents : BulletinListEvent
}