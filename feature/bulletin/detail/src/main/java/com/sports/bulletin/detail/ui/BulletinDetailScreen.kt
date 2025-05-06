package com.sports.bulletin.detail.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sports.bulletin.detail.ui.component.BulletinDetailMainContent
import com.sports.bulletin.detail.ui.component.EventHeader
import com.sports.bulletin.detail.ui.component.MarketOddsItem
import com.sports.component.domain.model.SportDomainModel
import com.sports.designsystem.component.AppText
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.LeftArrowIcon
import com.sports.designsystem.theme.AppTheme
import com.sports.ui.component.AppCenterTopAppBar
import com.sports.ui.component.AppLoading
import com.sports.ui.extentions.ObserveAsEvents
import com.sports.ui.extentions.TrackScreenViewEvent
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList

@Composable
fun BulletinDetailRoute(
    navigateBack: () -> Unit,
    viewModel: BulletinDetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            is BulletinDetailEvent.NavigateBack -> {
                navigateBack()
            }
        }
    }

    BulletinDetailScreen(uiState = uiState, onViewEvent = viewModel::onHandleViewEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BulletinDetailScreen(
    uiState: BulletinDetailViewState,
    onViewEvent: (BulletinDetailEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TrackScreenViewEvent(screenName = "BulletinDetail")
    AppLoading(isDisplayed = uiState.loading)
    Scaffold(
        topBar = {
            AppCenterTopAppBar(
                title = "BulletinDetail",
                onNavigationClick = {onViewEvent(BulletinDetailEvent.NavigateBack)},
                navigationIcon = AppIcons.LeftArrowIcon
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            when {
                uiState.loading -> {
                    // Yükleniyor Durumu
                    AppLoading(isDisplayed = true, modifier = Modifier.align(Alignment.Center))
                }
                uiState.error != null -> {
                    Column(
                        modifier = Modifier.fillMaxSize().padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AppText(text = "Detaylar yüklenirken hata oluştu.")
                        Spacer(modifier = Modifier.height(8.dp))
                        AppText(text = uiState.error, style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {}) {
                            Text("Tekrar Dene")
                        }
                    }
                }

                else -> {
                    BulletinDetailMainContent(
                        uiState = uiState,
                        onAddBetClick = { _, _, _, _ -> },
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true, name = "BulletinDetail - Success")
@Composable
private fun BulletinDetailSuccessPreview() {
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
        BulletinDetailScreen(
            uiState = BulletinDetailViewState(
                loading = false,
            ),
            onViewEvent = {},
        )
    }
}

@Preview(showBackground = true, name = "BulletinDetail - Loading")
@Composable
private fun BulletinDetailLoadingPreview() {
    AppTheme {
        BulletinDetailScreen(
            uiState = BulletinDetailViewState(loading = true),
            onViewEvent = {},
        )
    }
}

@Preview(showBackground = true, name = "BulletinDetail - Empty")
@Composable
private fun BulletinDetailEmptyPreview() {
    AppTheme {
        BulletinDetailScreen(
            uiState = BulletinDetailViewState(loading = false),
            onViewEvent = {},
        )
    }
}

@Preview
@Composable
private fun BulletinDetailPreview() {
    AppTheme {
        BulletinDetailScreen(
            uiState =
                BulletinDetailViewState(),
            onViewEvent = {},
        )
    }
}
