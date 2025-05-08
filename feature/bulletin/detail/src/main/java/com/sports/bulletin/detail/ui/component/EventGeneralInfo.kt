package com.sports.bulletin.detail.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.sports.component.domain.model.EventDetailDomainModel
import com.sports.designsystem.component.AppText
import com.sports.designsystem.theme.regular
import com.sports.designsystem.theme.semibold
import com.sports.ui.extentions.toReadableDateTime

@Composable
fun EventGeneralInfo(event: EventDetailDomainModel) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        shape = MaterialTheme.shapes.medium,
    ){
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
                .padding(bottom = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppText(
                text = event.sportTitle,
                style = MaterialTheme.typography.titleLarge.semibold,
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth()
                    .height(40.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AppText(
                    text = event.homeTeam,
                    style = MaterialTheme.typography.bodyLarge.semibold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                )

                AppText(
                    text = "VS",
                    style = MaterialTheme.typography.bodyLarge.regular,
                )
                AppText(
                    text = event.awayTeam,
                    style = MaterialTheme.typography.bodyLarge.semibold,
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center,
                    maxLines = 2,
                )

            }
            Spacer(Modifier.height(12.dp))
            AppText(
                text = event.startTime.toReadableDateTime(),
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }

}
