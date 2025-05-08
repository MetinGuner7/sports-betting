package com.sports.bulletin.detail.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.model.testEventDetailDomainModel
import com.sports.datastore.model.BasketItem
import com.sports.designsystem.component.AppText
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.MediumSpacer
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf


@Composable
fun BulletinDetailMainContent(
    eventDetail: EventDetailDomainModel?,
    currentBasketItems: ImmutableList<BasketItem>,
    onAddBetClick: (BasketItem) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (eventDetail == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AppText("Etkinlik detayları yükleniyor veya bulunamadı...")
        }
        return
    }
    val selectedSelectionIds = remember(currentBasketItems) {
        currentBasketItems.map { it.selectionId }.toSet()
    }
    Column {
        EventGeneralInfo(event = eventDetail)
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {

            if (eventDetail.bookmakers.isEmpty()) {
                item {
                    AppText("Bu etkinlik için bahis şirketi veya oran bulunamadı.")
                }
            } else {
                eventDetail.bookmakers.forEach { bookmaker ->
                    item(key = "bookmaker_header_${bookmaker.key ?: bookmaker.title}") {
                        BookmakerHeader(bookmaker = bookmaker)
                    }
                    if (bookmaker.markets.isEmpty()) {
                        item(key = "no_markets_${bookmaker.key ?: bookmaker.title}") {
                            AppText(
                                text = "${bookmaker.title ?: "Bilinmeyen Bahis Şirketi"} için market bulunamadı.",
                                modifier = Modifier.padding(start = 8.dp, top = 8.dp)
                            )
                        }
                    } else {
                        items(
                            items = bookmaker.markets,
                            key = { market -> "market_${bookmaker.key}_${market.key}" }
                        ) { market ->
                            MarketSectionCard(
                                market = market,
                                eventId = eventDetail.id,
                                selectedSelectionIds = selectedSelectionIds,
                                onOutcomeClick = { outcome ->
                                    onAddBetClick(
                                        BasketItem(
                                            selectionId = "${eventDetail.id}_${bookmaker.key}_${market.key}_${outcome.name}",
                                            eventId = eventDetail.id,
                                            marketKey = market.key,
                                            outcomeName = outcome.name,
                                            outcomePrice = outcome.price,
                                            homeTeam = eventDetail.homeTeam,
                                            awayTeam = eventDetail.awayTeam,
                                            bookmakerKey = bookmaker.key ?:"",
                                        )
                                    )
                                },
                                bookmakerKey = bookmaker.key ?:"",
                            )
                            MediumSpacer()
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "BulletinDetail - Success")
@Composable
private fun BulletinDetailMainContentSuccessPreview() {
    AppTheme {
        BulletinDetailMainContent(
            eventDetail = testEventDetailDomainModel(),
            currentBasketItems = persistentListOf(),
            onAddBetClick = { },
        )
    }
}

@Preview(showBackground = true, name = "BulletinDetail - Item Selected")
@Composable
private fun BulletinDetailMainContentItemSelectedPreview() {
    val eventDetail = testEventDetailDomainModel()
    val sampleBasketItems = if (eventDetail.bookmakers.isNotEmpty() &&
        eventDetail.bookmakers.first().markets.isNotEmpty() &&
        eventDetail.bookmakers.first().markets.first().outcomes.isNotEmpty()
    ) {
        val bookmaker = eventDetail.bookmakers.first()
        val market = eventDetail.bookmakers.first().markets.first()
        val outcome = market.outcomes.first()
        persistentListOf(
            BasketItem(
                selectionId = "${eventDetail.id}_${market.key}_${outcome.name}",
                eventId = eventDetail.id,
                marketKey = market.key,
                homeTeam = eventDetail.homeTeam,
                awayTeam = eventDetail.awayTeam,
                outcomeName = outcome.name,
                outcomePrice = outcome.price,
                bookmakerKey = bookmaker.key ?:""
            )
        )
    } else {
        persistentListOf()
    }

    AppTheme {
        BulletinDetailMainContent(
            eventDetail = testEventDetailDomainModel(),
            currentBasketItems = sampleBasketItems,
            onAddBetClick = { },
        )
    }
}
