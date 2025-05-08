package com.sports.basket.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.basket.ui.BasketEvent
import com.sports.datastore.model.BasketItem
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.XXLargeSpacer
import com.sports.designsystem.theme.semibold
import com.sports.ui.component.AppButton
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BasketContent(
    basketItems: ImmutableList<BasketItem>,
    totalOdds: Double,
    onViewEvent: (BasketEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(basketItems, key = { item -> item.selectionId }) { item ->
            BasketItemContent(
                item = item,
                onRemoveClick = {
                    onViewEvent(BasketEvent.RemoveItem(item.selectionId))
                },
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        if (basketItems.isNotEmpty()) {
            item {
                BasketSummarySection(
                    totalOdds = totalOdds,
                    onConfirmClick = {},
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun BasketSummarySection(
    totalOdds: Double,
    onConfirmClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.End,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 16.dp, bottom = 16.dp)
    ) {
        val formattedTotalOdds = "%.2f".format(totalOdds)
        Text(
            text = "Toplam Oran: $formattedTotalOdds",
            style = MaterialTheme.typography.titleMedium.semibold,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        XXLargeSpacer()
        AppButton(
            text = "Onayla",
            onClick = onConfirmClick,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true, name = "Sepet Özeti Bölümü")
@Composable
private fun BasketSummarySectionPreview() {
    AppTheme {
        BasketSummarySection(totalOdds = 5.75, onConfirmClick = {})
    }
}

@Preview(showBackground = true, name = "Sepet İçeriği - Dolu")
@Composable
private fun BasketContentWithItemsPreview() {
    val sampleItems = persistentListOf(
        BasketItem(
            selectionId = "sel1", eventId = "evt1", marketKey = "mk1", bookmakerKey = "bm1",
            homeTeam = "Ev Sahibi A", awayTeam = "Deplasman B",
            outcomeName = "1", outcomePrice = 1.85
        ),
        BasketItem(
            selectionId = "sel2", eventId = "evt2", marketKey = "mk2", bookmakerKey = "bm2",
            homeTeam = "Takım C", awayTeam = "Takım D",
            outcomeName = "X (Beraberlik)", outcomePrice = 3.20
        )
    )
    AppTheme {
        BasketContent(
            basketItems = sampleItems,
            totalOdds = 1.85 * 3.20,
            onViewEvent = {}
        )
    }
}

@Preview(showBackground = true, name = "Sepet İçeriği - Boş")
@Composable
private fun BasketContentEmptyPreview() {
    AppTheme {
        BasketContent(
            basketItems = persistentListOf(),
            totalOdds = 0.0,
            onViewEvent = {}
        )
    }
}
