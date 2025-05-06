package com.sports.bulletin.detail.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.bulletin.detail.ui.BulletinDetailViewState
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.model.Market
import com.sports.component.domain.model.Outcome
import com.sports.designsystem.component.AppText
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.semibold
import kotlinx.collections.immutable.persistentListOf


@Composable
fun BulletinDetailMainContent(
    uiState: BulletinDetailViewState,
    onAddBetClick: (eventId: String, marketKey: String, outcomeName: String, price: Double) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(uiState.eventDetails){
            Card (
                modifier = modifier
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = MaterialTheme.shapes.medium
            ){
                EventHeader(event = it)
                if (it.markets.isEmpty()){
                    Column (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text("- - -")
                    }

                } else {
                    it.markets.forEach { market ->
                        MarketOddsItem(
                            market = market,
                            onOddClick = { outcome ->
                                onAddBetClick(
                                    uiState.eventDetail?.id ?:"",
                                    market.key,
                                    outcome.name,
                                    outcome.price
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun EventHeader(event: EventDetailDomainModel?) {
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        AppText(
            text = "${event?.homeTeam} vs ${event?.awayTeam}",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(Modifier.height(4.dp))
        AppText(
            text = "Lig: ${event?.sportTitle}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun MarketOddsItem(
    market: Market,
    onOddClick: (outcome: Outcome) -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        AppText(
            text = market.key,
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            market.outcomes.forEach { outcome ->
                Card(
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    shape = MaterialTheme.shapes.medium,
                    onClick = { onOddClick(outcome) },
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally) {
                        AppText(text = outcome.name)
                        AppText(
                            text = outcome.price.toString(),
                            style = MaterialTheme.typography.titleLarge.semibold,
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "BulletinDetail - Success")
@Composable
private fun BulletinDetailSuccessPreview() {
    AppTheme {
        val sampleOutcome1 = Outcome(name="Lakers", price=1.85)
        val sampleOutcome2 = Outcome(name="Celtics", price=2.10)
        val sampleMarket1 = Market(key="h2h", 
            outcomes= persistentListOf(sampleOutcome1, sampleOutcome2)
        )
        val sampleEvent = persistentListOf(EventDetailDomainModel(
            id = "1", sportTitle = "NBA", homeTeam = "Lakers", awayTeam = "Celtics",
            sportKey = "americanfootball_nfl",
            startTime = "",
            markets = persistentListOf(
                Market(key="h2h",
                    outcomes= persistentListOf(sampleOutcome1, sampleOutcome2)
                )
            )
        ),
            EventDetailDomainModel(
                id = "1", sportTitle = "NBA", homeTeam = "Lakers", awayTeam = "Celtics",
                sportKey = "americanfootball_nfl",
                startTime = "",
                markets = persistentListOf()
            ),
        )
        BulletinDetailMainContent(
            uiState = BulletinDetailViewState(
                loading = false,
                eventDetails = sampleEvent,
                markets = persistentListOf(sampleMarket1),
                error = null),
            onAddBetClick = {_,_,_,_ ->},
        )
    }
}
