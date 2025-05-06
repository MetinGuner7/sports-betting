package com.sports.bulletin.list.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.bulletin.list.ui.BulletinListScreen
import com.sports.bulletin.list.ui.BulletinListViewState
import com.sports.component.domain.model.SportDomainModel
import com.sports.designsystem.component.MainText
import com.sports.designsystem.theme.AppTheme

@Composable
fun SportListItem(
    sport: SportDomainModel,
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
        Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)) {
            MainText(
                text = sport.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            MainText(
                text = sport.group,
                style = MaterialTheme.typography.bodyMedium,
            )
            sport.description.let { desc ->
                Spacer(modifier = Modifier.height(6.dp))
                MainText(
                    text = desc,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true, name = "BulletinList - Error")
@Composable
private fun SportListItemErrorPreview() {
    AppTheme {
        val sampleSport =
            SportDomainModel(
                key = "key",
                group = "Soccer",
                title = "League ",
                description = "Desc ",
                isActive = true,
                hasOutrights = false
            )

        Box (modifier = Modifier.padding(16.dp)){
            SportListItem(
                sport = sampleSport,
                onClick = {},
            )
        }
    }
}
