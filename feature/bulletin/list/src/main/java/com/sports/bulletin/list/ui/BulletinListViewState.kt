package com.sports.bulletin.list.ui

import androidx.compose.runtime.Stable
import com.sports.common.base.IViewState
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.model.SportDomainModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class BulletinListViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val isLoadingSports: Boolean = true,
    val sportsList: ImmutableList<SportDomainModel> = persistentListOf(),
    val sportsListError: String? = null,
    val selectedSportKey: String? = null,
    val isLoadingEvents: Boolean = false,
    val eventsForSelectedSport: ImmutableList<EventDetailDomainModel> = persistentListOf(),
    val filteredEventsForSelectedSport: ImmutableList<EventDetailDomainModel> = persistentListOf(),
    val eventsError: String? = null,
    val searchQuery: String = "",
) : IViewState
