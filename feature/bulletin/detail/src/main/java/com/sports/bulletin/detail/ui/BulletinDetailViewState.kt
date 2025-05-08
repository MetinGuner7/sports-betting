package com.sports.bulletin.detail.ui

import androidx.compose.runtime.Stable
import com.sports.common.base.IViewState
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.datastore.model.BasketItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class BulletinDetailViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val eventDetail: EventDetailDomainModel? = null,
    val eventDetails: ImmutableList<EventDetailDomainModel> = persistentListOf(),
    val error: String? = null,
    val searchQuery: String = "",
    val key: String = "",
    val basketItems: ImmutableList<BasketItem> = persistentListOf(),
    ) : IViewState
