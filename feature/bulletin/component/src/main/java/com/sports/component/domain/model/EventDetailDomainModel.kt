package com.sports.component.domain.model

import com.squareup.moshi.Json
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class EventDetailDomainModel(
    val id: String,
    val sportKey: String,
    val sportTitle: String,
    val startTime: String,
    val homeTeam: String,
    val awayTeam: String,
    val bookmakers: List<Bookmaker>
)

@Serializable
data class Bookmaker(
    val key: String?,
    val title: String?,
    @Json(name = "last_update")
    val lastUpdate: String?,
    val markets: List<Market>
)

@Serializable
data class Market(
    val key: String,
    val lastUpdate: String?,
    val outcomes: List<Outcome>
)

@Serializable
data class Outcome(
    val name: String,
    val price: Double,
    val point: Double? = null
)

fun testEventDetailDomainModel (): EventDetailDomainModel {
    val sampleOutcomeH2HDallas = Outcome(name = "Dallas Cowboys", price = 2.40) // Decimal oran
    val sampleOutcomeH2HTampa = Outcome(name = "Tampa Bay Buccaneers", price = 1.65)
    val sampleMarketH2H = Market(
        key = "h2h",
        lastUpdate = "2021-06-10T13:33:18Z",
        outcomes = persistentListOf(sampleOutcomeH2HDallas, sampleOutcomeH2HTampa)
    )

    val sampleOutcomeSpreadDallas = Outcome(name = "Dallas Cowboys", price = 1.90, point = 6.5)
    val sampleOutcomeSpreadTampa = Outcome(name = "Tampa Bay Buccaneers", price = 1.90, point = -6.5)
    val sampleMarketSpreads = Market(
        key = "spreads",
        lastUpdate = "2021-06-10T13:33:18Z",
        outcomes = persistentListOf(sampleOutcomeSpreadDallas, sampleOutcomeSpreadTampa)
    )

    val sampleBookmaker1 = Bookmaker(
        key = "unibet",
        title = "Unibet",
        lastUpdate = "2021-06-10T13:33:18Z",
        markets = persistentListOf(sampleMarketH2H, sampleMarketSpreads)
    )

    val sampleBookmaker2 = Bookmaker(
        key = "caesars",
        title = "Caesars",
        lastUpdate = "2021-06-10T13:33:48Z",
        markets = persistentListOf(sampleMarketH2H)
    )

    val sampleEventDetail = EventDetailDomainModel(
        id = "bda33adca828c09dc3cac3a856aef176",
        sportKey = "americanfootball_nfl",
        sportTitle = "NFL",
        startTime = "2021-09-10T00:20:00Z",
        homeTeam = "Tampa Bay Buccaneers",
        awayTeam = "Dallas Cowboys",
        bookmakers = persistentListOf(sampleBookmaker1, sampleBookmaker2)
    )
    return sampleEventDetail
}
