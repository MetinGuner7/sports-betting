package com.sports.component.data.model

import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.model.Market
import com.sports.component.domain.model.Outcome
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@JsonClass(generateAdapter = true)
data class EventOddsDto(
    val id: String,
    @Json(name = "sport_key")
    val sportKey: String?,
    @Json(name = "sport_title")
    val sportTitle: String?,
    @Json(name = "commence_time")
    val commenceTime: String,
    @Json(name = "home_team")
    val homeTeam: String?,
    @Json(name = "away_team")
    val awayTeam: String?,
    val bookmakers: List<BookmakerDto>?
)

@JsonClass(generateAdapter = true)
data class BookmakerDto(
    val key: String?,
    val title: String?,
    @Json(name = "last_update")
    val lastUpdate: String?,
    val markets: List<MarketDto>?
)

@JsonClass(generateAdapter = true)
data class MarketDto(
    val key: String?,
    @Json(name = "last_update")
    val lastUpdate: String?,
    val outcomes: List<OutcomeDto>?
)

@JsonClass(generateAdapter = true)
data class OutcomeDto(
    val name: String?,
    val price: Double?,
    val point: Double? = null
)

fun EventOddsDto.toDomainModel(): EventDetailDomainModel? {
    if (this.sportKey == null || this.homeTeam == null || this.awayTeam == null) {
        return null
    }

    val marketsFromFirstBookmaker = this.bookmakers?.firstOrNull()?.markets

    return EventDetailDomainModel(
        id = this.id,
        sportKey = this.sportKey,
        sportTitle = this.sportTitle ?: "Unknown Sport",
        startTime = this.commenceTime,
        homeTeam = this.homeTeam,
        awayTeam = this.awayTeam,
        markets = marketsFromFirstBookmaker?.mapNotNull { it.toDomainModel() }?.toImmutableList() ?: persistentListOf()
    )
}

fun MarketDto.toDomainModel(): Market? {
    if (this.key == null || this.outcomes == null) return null

    return Market(
        key = this.key,
        outcomes = this.outcomes.mapNotNull { it.toDomainModel() }.toImmutableList()
    )
}

fun OutcomeDto.toDomainModel(): Outcome? {
    if (this.name == null || this.price == null) return null

    return Outcome(
        name = this.name,
        price = this.price,
        point = this.point
    )
}

fun List<EventOddsDto>.toEventDetailDomainModel(): List<EventDetailDomainModel> {
    return this.mapNotNull { it.toDomainModel() }
}
