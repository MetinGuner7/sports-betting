package com.sports.basket.ui

import androidx.compose.runtime.Stable
import com.sports.common.base.IViewState
import com.sports.datastore.model.BasketItem
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class BasketViewState(
    override val loading: Boolean = false,
    override val showErrorModal: Boolean = false,
    val basketItems: ImmutableList<BasketItem> = persistentListOf(),
    val totalOdds: Double = 0.0
) : IViewState
