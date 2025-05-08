package com.sports.bulletin.list.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.sports.bulletin.list.ui.BulletinListEvent
import com.sports.bulletin.list.ui.BulletinListViewState
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.model.SportDomainModel
import com.sports.designsystem.component.AppText
import com.sports.designsystem.theme.semibold
import com.sports.ui.extentions.toReadableDateTime

@Composable
fun MainContent(
    uiState: BulletinListViewState,
    modifier: Modifier = Modifier,
    onViewEvent: (BulletinListEvent) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ){
            SearchInput(
                modifier = Modifier.padding(horizontal = 16.dp),
                value = uiState.searchQuery,
                onValueChange = { query ->
                    onViewEvent(BulletinListEvent.OnSearchQueryChanged(query))
                },
                onClearClick = { onViewEvent(BulletinListEvent.OnSearchClearClicked) },
            )
        }
        if (uiState.isLoadingSports) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (uiState.sportsListError != null) {
            AppText(
                text = "Ligler yüklenemedi: ${uiState.sportsListError}",
                modifier = Modifier.padding(horizontal = 16.dp).align(Alignment.CenterHorizontally),
            )
        } else if (uiState.sportsList.isNotEmpty()) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(uiState.sportsList, key = { sport -> sport.key }) { sport ->
                    SportChip(
                        sport = sport,
                        isSelected = sport.key == uiState.selectedSportKey,
                        onClick = { onViewEvent(BulletinListEvent.OnSportSelected(sport.key)) }
                    )
                }
            }

        } else {
            AppText(
                text = "Gösterilecek lig bulunamadı.",
                modifier = Modifier.padding(horizontal = 16.dp).align(Alignment.CenterHorizontally)
            )
        }

        when {
            uiState.isLoadingEvents -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            uiState.eventsError != null -> {
                Column(
                    modifier = Modifier.fillMaxSize().padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AppText(text = "Maçlar yüklenirken hata oluştu.")
                    Spacer(modifier = Modifier.height(8.dp))
                    AppText(text = uiState.eventsError, style = MaterialTheme.typography.bodySmall)
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { onViewEvent(BulletinListEvent.RetryLoadEvents) }) {
                        Text("Tekrar Dene")
                    }
                }
            }
            uiState.selectedSportKey != null && uiState.eventsForSelectedSport.isEmpty() && !uiState.isLoadingEvents -> {
                Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
                    AppText(text = "Bu lig için gösterilecek maç bulunamadı.")
                }
            }
            uiState.filteredEventsForSelectedSport.isNotEmpty() -> {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(
                        items = uiState.filteredEventsForSelectedSport,
                        key = { event -> event.id }
                    ) { event ->
                        EventListItem(
                            event = event,
                            onClick = {
                                onViewEvent(BulletinListEvent.NavigateToEventDetail(event))
                            }
                        )
                    }
                }
            }
            uiState.selectedSportKey == null && !uiState.isLoadingSports && uiState.sportsList.isNotEmpty() -> {
                Box(modifier = Modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
                    AppText(text = "Lütfen bir lig seçin.")
                }
            }
        }
    }
}

@Composable
fun SportChip(
    sport: SportDomainModel,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = isSelected,
        onClick = onClick,
        label = { AppText(text = sport.title,
            style = MaterialTheme.typography.bodySmall.copy(
                color = if (isSelected) MaterialTheme.colorScheme.surface
                else MaterialTheme.colorScheme.onSurface
            ),
            maxLines = 1, overflow = TextOverflow.Ellipsis) },
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
    )
}

@Composable
fun EventListItem(
    event: EventDetailDomainModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Column (
                    modifier = Modifier.weight(.5f)
                        .padding(horizontal = 12.dp)
                        .height(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    AppText(
                        textAlign = TextAlign.Center,
                        text = event.homeTeam,
                        maxLines = 2,
                        style = MaterialTheme.typography.bodyMedium.semibold,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                AppText(
                    text = "VS",
                    style = MaterialTheme.typography.bodySmall,
                )
                Column(
                    modifier = Modifier.weight(.5f)
                        .padding(horizontal = 12.dp)
                        .height(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    AppText(
                        textAlign = TextAlign.Center,
                        text = event.awayTeam,
                        maxLines = 2,
                        style = MaterialTheme.typography.bodyMedium.semibold,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            AppText(
                text = event.startTime.toReadableDateTime(),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
