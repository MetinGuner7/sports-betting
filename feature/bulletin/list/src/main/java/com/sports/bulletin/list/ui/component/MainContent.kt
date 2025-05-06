package com.sports.bulletin.list.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sports.bulletin.list.ui.BulletinListEvent
import com.sports.component.domain.model.SportDomainModel
import com.sports.designsystem.component.AppText
import com.sports.ui.extentions.containsEmoji
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun MainContent(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    sports: ImmutableList<SportDomainModel> = persistentListOf(),
    error: String? = null,
    searchQuery: String = "",
    onViewEvent: (BulletinListEvent) -> Unit,
){
    Column(modifier = modifier.fillMaxSize().padding(16.dp)) {
        SearchInput(
            value = searchQuery,
            onValueChange = {
                if (it.containsEmoji().not()) {
                    onViewEvent.invoke(BulletinListEvent.OnKeyChanged(it))
                }
            },
            onClearClick = { onViewEvent.invoke(BulletinListEvent.OnCloseIcon) },
        )
        if (error != null) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AppText(text = "Veriler yüklenirken hata oluştu.")
                    Spacer(modifier = Modifier.height(8.dp))
                    AppText(
                        text = error,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                }
            }
        } else if (isLoading.not() && sports.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                AppText(text = "Gösterilecek spor etkinliği bulunamadı.")
            }
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = sports,
                    key = { sport -> sport.key }
                ) { sport ->
                    SportListItem(
                        sport = sport,
                        onClick = {
                            onViewEvent(BulletinListEvent.NavigateToBulletinDetail(sport.key))
                        }
                    )
                }
            }
        }
    }
}

