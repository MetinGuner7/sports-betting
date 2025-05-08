package com.sports.basket.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sports.basket.ui.component.BasketContent
import com.sports.datastore.model.BasketItem
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.LeftArrowIcon
import com.sports.ui.component.AppCenterTopAppBar
import com.sports.ui.extentions.ObserveAsEvents
import com.sports.ui.extentions.TrackScreenViewEvent
import kotlinx.collections.immutable.persistentListOf

@Composable
fun BasketRoute(
    navigateBack: () -> Unit,
    viewModel: BasketViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            is BasketEvent.NavigateBack -> {
                navigateBack()
            }
        }
    }

    BasketScreen(
        uiState = uiState,
        onViewEvent = viewModel::onHandleViewEvent,
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketScreen(
    uiState: BasketViewState,
    onViewEvent: (BasketEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TrackScreenViewEvent(screenName = "Basket")

    Scaffold(
        topBar = {
            AppCenterTopAppBar(
                title = "Sepetim",
                onNavigationClick = { onViewEvent(BasketEvent.NavigateBack) },
                navigationIcon = AppIcons.LeftArrowIcon
            )
        },
        modifier = modifier
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            if (uiState.loading && uiState.basketItems.isEmpty()) {
                LoadingState()
            } else if (uiState.basketItems.isEmpty()) {
                EmptyBasketState()
            } else {
                BasketContent(
                    basketItems = uiState.basketItems,
                    onViewEvent = onViewEvent,
                    totalOdds = uiState.totalOdds
                )
            }
        }
    }
}

@Composable
private fun LoadingState(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptyBasketState(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize().padding(16.dp), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Boş Sepet",
                modifier = Modifier.height(48.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("Sepetiniz şu anda boş.")
        }
    }
}

class SampleBasketItemProvider : PreviewParameterProvider<BasketItem> {
    override val values = sequenceOf(
        BasketItem(
            selectionId = "1", eventId = "event1", marketKey = "mk1",
            homeTeam = "Ev Sahibi A Takımı", awayTeam = "Deplasman B Takımı",
            outcomeName = "Ev Sahibi Kazanır", outcomePrice = 1.85, bookmakerKey = "Unibet"
        ),
        BasketItem(
            selectionId = "2", eventId = "event2", marketKey = "mk2",
            homeTeam = "Takım C", awayTeam = "Takım D",
            outcomeName = "Toplam Gol 2.5 Üst", outcomePrice = 2.10, bookmakerKey = "Unibet"
        )
    )
}

@Preview(name = "Basket Screen - Loading", showBackground = true)
@Composable
fun BasketScreenLoadingPreview() {
    MaterialTheme {
        BasketScreen(
            uiState = BasketViewState(loading = true, basketItems = persistentListOf()),
            onViewEvent = {}
        )
    }
}

@Preview(name = "Basket Screen - Empty", showBackground = true)
@Composable
fun BasketScreenEmptyPreview() {
    MaterialTheme {
        BasketScreen(
            uiState = BasketViewState(loading = false, basketItems = persistentListOf()),
            onViewEvent = {}
        )
    }
}

@Preview(name = "Basket Screen - With Items", showBackground = true)
@Composable
fun BasketScreenWithItemsPreview() {
    val sampleItems = persistentListOf(
        BasketItem(
            selectionId = "1", eventId = "event1", marketKey = "mk1",
            homeTeam = "Ev Sahibi A", awayTeam = "Deplasman B",
            outcomeName = "1", outcomePrice = 1.85, bookmakerKey = "Unibet"
        ),
        BasketItem(
            selectionId = "2", eventId = "event2", marketKey = "mk2",
            homeTeam = "Takım C", awayTeam = "Takım D",
            outcomeName = "X", outcomePrice = 3.20, bookmakerKey = "Unibet"
        ),
        BasketItem(
            selectionId = "3", eventId = "event3", marketKey = "mk3",
            homeTeam = "FC Z", awayTeam = "AC Y",
            outcomeName = "Toplam Gol 2.5 Alt", outcomePrice = 1.90, bookmakerKey = "Unibet"
        )
    )
    MaterialTheme {
        BasketScreen(
            uiState = BasketViewState(
                loading = false,
                basketItems = sampleItems,
                totalOdds = 1.85 * 3.20 * 1.90
            ),
            onViewEvent = {}
        )
    }
}

@Preview(name = "Basket Screen - Error", showBackground = true)
@Composable
fun BasketScreenErrorPreview() {
    MaterialTheme {
        BasketScreen(
            uiState = BasketViewState(
                loading = false,
            ),
            onViewEvent = {}
        )
    }
}
