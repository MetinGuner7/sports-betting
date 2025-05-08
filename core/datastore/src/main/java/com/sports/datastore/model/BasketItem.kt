package com.sports.datastore.model

import com.sports.core.datastore.BasketItemProto
import com.sports.core.datastore.OutcomeProto

data class BasketItem(
    val selectionId: String,
    val eventId: String,
    val marketKey: String,
    val outcomeName: String,
    val outcomePrice: Double,
    val homeTeam: String,
    val awayTeam: String,
    val bookmakerKey: String,
)

data class BasketSummary(
    val itemCount: Int,
    val totalOdds: Double
)


fun BasketItemProto.toDomainModel(): BasketItem {
    return BasketItem(
        selectionId = this.selectionId,
        eventId = this.eventId,
        marketKey = this.marketKey,
        outcomeName = this.outcome.name,
        outcomePrice = this.outcome.price,
        homeTeam = this.homeTeam,
        awayTeam = this.awayTeam,
        bookmakerKey = this.bookmakerKey
    )
}

fun mapToBasketItemProto(
    eventId: String,
    marketKey: String,
    name: String,
    price: Double,
    homeTeam: String,
    awayTeam: String,
    selectionId: String,
    bookmakerKey: String,
): BasketItemProto {
    return BasketItemProto.newBuilder()
        .setSelectionId(selectionId)
        .setEventId(eventId)
        .setMarketKey(marketKey)
        .setHomeTeam(homeTeam)
        .setAwayTeam(awayTeam)
        .setBookmakerKey(bookmakerKey)
        .setOutcome(
            OutcomeProto.newBuilder()
                .setName(name)
                .setPrice(price)
                .build()
        ).build()
}
