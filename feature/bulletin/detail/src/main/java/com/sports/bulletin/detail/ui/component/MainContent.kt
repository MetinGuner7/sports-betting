package com.sports.bulletin.detail.ui.component

import androidx.compose.ui.draw.clip
import com.sports.ui.extentions.toReadableDateTime
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.component.domain.model.Bookmaker
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.model.Market
import com.sports.component.domain.model.Outcome
import com.sports.component.domain.model.testEventDetailDomainModel
import com.sports.designsystem.component.AppText
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.semibold
import kotlinx.collections.immutable.persistentListOf


@Composable
fun BulletinDetailMainContent(
    eventDetail: EventDetailDomainModel?,
    onAddBetClick: (eventId: String, marketKey: String, outcomeName: String, price: Double, point: Double?) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (eventDetail == null) {
        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AppText("Etkinlik detayları yükleniyor veya bulunamadı...")
        }
        return
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
                                onOutcomeClick = { outcome ->
                                    onAddBetClick(
                                        eventDetail.id,
                                        market.key,
                                        outcome.name,
                                        outcome.price,
                                        outcome.point
                                    )
                                },
                                modifier = Modifier.padding(top = 8.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EventGeneralInfo(event: EventDetailDomainModel) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .padding(bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppText(
            text = event.sportTitle,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppText(
                text = event.homeTeam,
                style = MaterialTheme.typography.headlineSmall.semibold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            AppText(
                text = "VS",
                style = MaterialTheme.typography.bodyLarge.semibold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            AppText(
                text = event.awayTeam,
                style = MaterialTheme.typography.headlineSmall.semibold,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(Modifier.height(4.dp))
        AppText(
            text = event.startTime.toReadableDateTime(),
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun BookmakerHeader(bookmaker: Bookmaker) {
    Column(modifier = Modifier.fillMaxWidth()) {
        bookmaker.title?.let { title ->
            AppText(
                text = title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        bookmaker.lastUpdate?.let { update ->
            AppText(
                text = "Son Güncelleme: ${update.toReadableDateTime()}",
                style = MaterialTheme.typography.labelSmall,
            )
        }
    }
}


@Composable
fun MarketSectionCard(
    market: Market,
    onOutcomeClick: (outcome: Outcome) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = MaterialTheme.shapes.medium,
    ) {
        Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp)) {
            AppText(
                text = market.key.let { marketKey ->
                    when (marketKey) {
                        "h2h" -> "Maç Sonucu"
                        "spreads" -> "Handikaplı Maç Sonucu"
                        "totals" -> "Toplam Sayı (Alt/Üst)"
                        else -> marketKey.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
                    }
                },
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 10.dp)
            )

            market.outcomes.forEach { outcome ->
                OutcomeRow(
                    outcome = outcome,
                    onClick = { onOutcomeClick(outcome) }
                )
                if (outcome != market.outcomes.last()) {
                    Spacer(Modifier.height(4.dp))
                }
            }
        }
    }
}

@Composable
fun OutcomeRow(
    outcome: Outcome,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = outcome.name + (outcome.point?.let { p -> " (${if (p > 0) "+" else ""}$p)" } ?: ""),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            AppText(
                text = outcome.price.toString(), // Oran
                style = MaterialTheme.typography.titleSmall.semibold, // Oran stili
            )
        }
    }
}


@Preview(showBackground = true, name = "BulletinDetail - Success")
@Composable
private fun BulletinDetailMainContentSuccessPreview() {
    AppTheme {
        BulletinDetailMainContent(
            eventDetail = testEventDetailDomainModel(),
            onAddBetClick = { _, _, _, _, _ -> },
        )
    }
}