package com.sports.component.domain.model

import kotlinx.collections.immutable.ImmutableList

data class EventDetailDomainModel(
    val id: String,
    val sportKey: String,
    val sportTitle: String,
    val startTime: String,
    val homeTeam: String,
    val awayTeam: String,
    val markets: ImmutableList<Market>
)

data class Market(
    val key: String,
    val outcomes: ImmutableList<Outcome>
)

data class Outcome(
    val name: String,
    val price: Double,
    val point: Double? = null
)