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
import com.sports.component.domain.model.SportDomainModel
import com.sports.designsystem.theme.AppTheme
import com.sports.ui.component.AppCenterTopAppBar
import com.sports.ui.component.AppLoading
import com.sports.ui.extentions.ObserveAsEvents
import com.sports.ui.extentions.TrackScreenViewEvent
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun BulletinListRoute(
    navigateToLogin: () -> Unit,
    navigateToBulletinDetail: (String) -> Unit,
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
            is BulletinListEvent.NavigateToBulletinDetail -> {
                navigateToBulletinDetail(event.key)
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
                title = "BulletinList",
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(paddingValues = padding)
                .fillMaxSize()
        ) {
            MainContent(
                isLoading = uiState.loading,
                modifier = Modifier,
                sports = uiState.filteredSports,
                error = uiState.error,
                searchQuery = uiState.searchQuery,
                onViewEvent = onViewEvent,
            )
        }
    }
}


@Preview(showBackground = true, name = "BulletinList - Success")
@Composable
private fun BulletinListSuccessPreview() {
    AppTheme {
        val sampleSports = List(5) { index ->
            SportDomainModel(
                key = "key$index",
                group = "Soccer",
                title = "League $index",
                description = "Desc $index",
                isActive = true,
                hasOutrights = false
            )
        }
        BulletinListScreen(
            uiState = BulletinListViewState(
                loading = false,
                filteredSports = sampleSports.toPersistentList()
            ),
            onViewEvent = {},
        )
    }
}

@Preview(showBackground = true, name = "BulletinList - Loading")
@Composable
private fun BulletinListLoadingPreview() {
    AppTheme {
        BulletinListScreen(
            uiState = BulletinListViewState(loading = true),
            onViewEvent = {},
        )
    }
}

@Preview(showBackground = true, name = "BulletinList - Empty")
@Composable
private fun BulletinListEmptyPreview() {
    AppTheme {
        BulletinListScreen(
            uiState = BulletinListViewState(loading = false, allSports = persistentListOf()),
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
