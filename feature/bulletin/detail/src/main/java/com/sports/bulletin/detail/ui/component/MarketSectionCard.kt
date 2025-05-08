package com.sports.bulletin.detail.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sports.component.domain.model.Market
import com.sports.component.domain.model.Outcome
import com.sports.designsystem.component.AppText

@Composable
fun MarketSectionCard(
    market: Market,
    eventId: String,
    selectedSelectionIds: Set<String>,
    bookmakerKey: String,
    onOutcomeClick: (outcome: Outcome) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
        ),
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
                val currentSelectionId = remember(eventId, market.key, outcome.name) {
                    "${eventId}_${bookmakerKey}_${market.key}_${outcome.name}"
                }
                val isSelected = remember(selectedSelectionIds, currentSelectionId) {
                    selectedSelectionIds.contains(currentSelectionId)
                }

                OutcomeRow(
                    outcome = outcome,
                    isSelected = isSelected,
                    onClick = { onOutcomeClick(outcome) }
                )
                if (outcome != market.outcomes.last()) {
                    Spacer(Modifier.height(4.dp))
                }
            }
        }
    }
}
