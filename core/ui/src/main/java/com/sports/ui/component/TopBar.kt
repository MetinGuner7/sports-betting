package com.sports.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.designsystem.component.AppText
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.LeftArrowIcon
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.semibold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppCenterTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
    navigationIcon: ImageVector? = null,
    navigationIconContentDescription: String? = null,
    actionIcon: ImageVector? = null,
    actionIconContentDescription: String? = null,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary,
        titleContentColor = MaterialTheme.colorScheme.surface
    ),
    onNavigationClick: () -> Unit = {},
    onActionClick: () -> Unit = {},
) {
    CenterAlignedTopAppBar(
        title = {
            AppText(
                text = title,
                style = MaterialTheme.typography.titleMedium.semibold,
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            navigationIcon?.let { icon ->
                IconButton(onClick = { onNavigationClick() }) {
                    Image(imageVector = icon,
                        contentDescription = navigationIconContentDescription,
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.surface)
                    )
                }
            }
        },
        actions = {
            actionIcon?.let {
                IconButton(onClick = { onActionClick() }) {
                    Image(
                        imageVector = actionIcon,
                        contentDescription = actionIconContentDescription,
                        colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.surface)
                    )
                }
            }
        },
        colors = colors,
        modifier = modifier.testTag("FrinkTopAppBar"),
    )
}

@Composable
fun AppBottomSheetTopAppBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp, bottom = 9.dp),
            horizontalArrangement = Arrangement.Center,
        ) {
            AppText(
                text = title,
                style = MaterialTheme.typography.titleMedium.semibold,
                textAlign = TextAlign.Center,
            )
        }
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.secondary,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Top App Bar")
@Composable
private fun AppTopAppBarPreview() {
    AppTheme {
        AppCenterTopAppBar(
            title = "deneme",
            navigationIcon = AppIcons.LeftArrowIcon,
            navigationIconContentDescription = "Navigation icon",
            actionIcon = Icons.Outlined.MoreVert,
            actionIconContentDescription = "Action icon",
        )
    }
}

@Preview("Top App Bar")
@Composable
private fun AppBottomSheetTopAppBarPreview() {
    AppTheme { AppBottomSheetTopAppBar(title = "deneme") }
}
