package com.sports.bulletin.list.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sports.bulletin.list.ui.component.MainContent
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.component.domain.model.SportDomainModel
import com.sports.designsystem.theme.AppTheme
import com.sports.ui.component.AppCenterTopAppBar
import com.sports.ui.component.AppLoading
import com.sports.ui.extentions.ObserveAsEvents
import com.sports.ui.extentions.TrackScreenViewEvent
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BulletinListRoute(
    navigateToLogin: () -> Unit,
    navigateToBulletinDetail: (EventDetailDomainModel) -> Unit,
    viewModel: BulletinListViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            is BulletinListEvent.NavigateBack -> {

            }
            is BulletinListEvent.NavigateToLogin -> {
                navigateToLogin()
            }
            is BulletinListEvent.NavigateToEventDetail -> {
                navigateToBulletinDetail(event.sport)
            }
        }
    }

    BulletinListScreen(uiState = uiState, onViewEvent = viewModel::onHandleViewEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BulletinListScreen(
    uiState: BulletinListViewState,
    onViewEvent: (BulletinListEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TrackScreenViewEvent(screenName = "BulletinList")
    AppLoading(isDisplayed = uiState.loading)
    Scaffold(
        topBar = {
            AppCenterTopAppBar(
                title = "Ligler",
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(paddingValues = padding)
                .fillMaxSize()
        ) {
            MainContent(
                uiState = uiState,
                modifier = Modifier,
                onViewEvent = onViewEvent,
            )
        }
    }
}


@Preview(showBackground = true, name = "BulletinList - Success (Leagues & Events Loaded)")
@Composable
private fun BulletinListSuccessPreview() {
    AppTheme {
        val sampleSports = persistentListOf(
            SportDomainModel(key = "soccer_epl", group = "Soccer", title = "Premier League", description = "English Premier League", isActive = true, hasOutrights = false),
            SportDomainModel(key = "basketball_nba", group = "Basketball", title = "NBA", description = "US Basketball", isActive = true, hasOutrights = false),
            SportDomainModel(key = "americanfootball_nfl", group = "Football", title = "NFL", description = "US Football", isActive = true, hasOutrights = false)
        )

        val sampleEvents = persistentListOf(
            EventDetailDomainModel(id = "evt1", sportKey = "soccer_epl", sportTitle = "Premier League", startTime = "2025-05-10T18:00:00Z", homeTeam = "Man City", awayTeam = "Arsenal", bookmakers = persistentListOf()),
            EventDetailDomainModel(id = "evt2", sportKey = "soccer_epl", sportTitle = "Premier League", startTime = "2025-05-10T20:30:00Z", homeTeam = "Liverpool", awayTeam = "Chelsea", bookmakers = persistentListOf())
        )

        BulletinListScreen(
            uiState = BulletinListViewState(
                isLoadingSports = false,
                sportsList = sampleSports,
                selectedSportKey = "soccer_epl",
                isLoadingEvents = false,
                eventsForSelectedSport = sampleEvents,
                filteredEventsForSelectedSport = sampleEvents,
                searchQuery = ""
            ),
            onViewEvent = {},
        )
    }
}


@Preview
@Composable
private fun BulletinListPreview() {
    AppTheme {
        BulletinListScreen(
            uiState =
                BulletinListViewState(),
            onViewEvent = {},
        )
    }
}
