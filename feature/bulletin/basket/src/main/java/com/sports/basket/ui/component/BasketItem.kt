package com.sports.basket.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.sports.basket.R
import com.sports.basket.ui.SampleBasketItemProvider
import com.sports.datastore.model.BasketItem
import com.sports.designsystem.component.AppText
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.Cancel
import com.sports.designsystem.theme.MediumSpacer
import com.sports.designsystem.theme.regular
import com.sports.designsystem.theme.semibold

@Composable
fun BasketItemContent(
    item: BasketItem,
    onRemoveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = MaterialTheme.shapes.medium
    ) {
        Column {
            Row (modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp),
            ){
                Column(modifier = Modifier.weight(.9f)) {
                    Column (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        AppText(
                            text = item.homeTeam,
                            style = MaterialTheme.typography.bodyMedium.semibold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = stringResource(R.string.versus),
                            style = MaterialTheme.typography.labelSmall.regular)
                        AppText(
                            text = item.awayTeam,
                            style = MaterialTheme.typography.bodyMedium.semibold,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
                Column(modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center){
                    IconButton(onClick = onRemoveClick) {
                        Icon(
                            imageVector = AppIcons.Cancel,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    }
                }
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Row {
                    AppText(
                        text = stringResource(R.string.choice, item.outcomeName),
                        style = MaterialTheme.typography.bodyMedium.copy(
                                color = MaterialTheme.colorScheme.outlineVariant
                            ),
                    )
                    AppText(
                        text = " ${item.outcomeName}" ,
                        style = MaterialTheme.typography.bodyMedium.semibold,
                    )
                }
                Row {
                    AppText(
                        text = stringResource(R.string.stake),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = MaterialTheme.colorScheme.outlineVariant
                        ),
                    )
                    AppText(
                        text = " %.2f".format(item.outcomePrice),
                        style = MaterialTheme.typography.bodyMedium.semibold,
                    )
                }
            }
            MediumSpacer()
        }
    }
}

@Preview(name = "Basket Item Row", showBackground = true)
@Composable
fun BasketItemRowPreview(@PreviewParameter(SampleBasketItemProvider::class) item: BasketItem) {
    MaterialTheme {
        BasketItemContent(item = item, onRemoveClick = {},
            modifier = Modifier.height(80.dp))
    }
}
