package com.sports.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.designsystem.component.MainText
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.EmptyList
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.MediumSpacer
import com.sports.designsystem.theme.XLargeSpacer
import com.sports.designsystem.theme.semibold

@Composable
fun EmptyPageView(
    icon: ImageVector,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(icon, contentDescription = null)
        XLargeSpacer()
        MainText(text = title,
            style = MaterialTheme.typography.bodyLarge.semibold)
        MediumSpacer()
        MainText(
            text = description,
            style =
                MaterialTheme.typography.bodyMedium.copy(color = MaterialTheme.colorScheme.tertiary),
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
@Preview
private fun EmptyPageViewPreview() {
    AppTheme {
        Scaffold {
            EmptyPageView(
                modifier = Modifier.padding(it).background(Color.White).fillMaxSize(),
                icon = AppIcons.EmptyList,
                title = "Title",
                description = "Description",
            )
        }
    }
}
