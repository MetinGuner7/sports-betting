package com.sports.home.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sports.designsystem.theme.AppTheme
import com.sports.ui.component.AppCenterTopAppBar
import com.sports.ui.component.AppLoading
import com.sports.ui.extentions.ObserveAsEvents
import com.sports.ui.extentions.TrackScreenViewEvent


@Composable
fun HomeRoute(
    navigateToLogin: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents (viewModel.event) { event ->
        when (event) {
            is HomeEvent.NavigateBack -> {

            }
            is HomeEvent.NavigateToLogin -> {
                navigateToLogin()
            }
        }
    }

    HomeScreen(uiState = uiState, onViewEvent = viewModel::onHandleViewEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeViewState,
    onViewEvent: (HomeEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TrackScreenViewEvent(screenName = "Home")
    AppLoading(isDisplayed = uiState.loading)
    Scaffold(
        topBar = {
            AppCenterTopAppBar(
                title = "Home",
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item {

            }
        }
    }
}

@Preview
@Composable
private fun HomePreview() {
    AppTheme  {
        HomeScreen(
            uiState =
                HomeViewState(),
            onViewEvent = {},
        )
    }
}
