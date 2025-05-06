package com.sports.bulletin.list.ui

import androidx.compose.runtime.Stable
import com.sports.common.base.IViewState
import com.sports.component.domain.model.SportDomainModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class BulletinListViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val allSports: ImmutableList<SportDomainModel> = persistentListOf(),
    val filteredSports: ImmutableList<SportDomainModel> = persistentListOf(),
    val error: String? = null,
    val searchQuery: String = "",
    ) : IViewState
