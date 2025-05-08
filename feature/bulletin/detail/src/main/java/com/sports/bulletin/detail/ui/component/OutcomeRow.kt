package com.sports.bulletin.detail.ui.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.component.domain.model.Outcome
import com.sports.designsystem.component.AppText
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.semibold

@Composable
fun OutcomeRow(
    outcome: Outcome,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) {
        MaterialTheme.colorScheme.surfaceDim.copy(alpha = 0.1f)
    } else {
        MaterialTheme.colorScheme.surface
    }
    val contentColor = if (isSelected) {
        MaterialTheme.colorScheme.onPrimaryContainer
    } else {
        MaterialTheme.colorScheme.onSurface
    }
    val borderColor = if (isSelected) {
        MaterialTheme.colorScheme.surfaceDim
    } else {
        MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.small)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.small,
        color = backgroundColor,
        contentColor = contentColor,
        border = BorderStroke(1.dp, borderColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            AppText(
                text = outcome.name + (outcome.point?.let { p -> " (${if (p > 0) "+" else ""}$p)" } ?: ""),
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = contentColor
                ),
                modifier = Modifier.weight(1f).padding(end = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            AppText(
                text = "%.2f".format(outcome.price),
                style = MaterialTheme.typography.titleSmall.semibold.copy(
                    color = contentColor
                ),
            )
        }
    }
}

@Preview(showBackground = true, name = "OutcomeRow - Not Selected")
@Composable
private fun OutcomeRowNotSelectedPreview() {
    AppTheme {
        OutcomeRow(
            outcome = Outcome(name = "Ev Sahibi Kazanır", price = 1.85),
            isSelected = false,
            onClick = {}
        )
    }
}

@Preview(showBackground = true, name = "OutcomeRow - Selected")
@Composable
private fun OutcomeRowSelectedPreview() {
    AppTheme {
        OutcomeRow(
            outcome = Outcome(name = "Deplasman Kazanır", price = 3.20, point = -1.5),
            isSelected = true,
            onClick = {}
        )
    }
}
