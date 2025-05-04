package com.sports.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.Cancel
import com.sports.designsystem.icons.CheckCircle
import com.sports.designsystem.icons.Info
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.LocalAppColors
import com.sports.designsystem.theme.MediumSpacer
import com.sports.designsystem.theme.fullRadius
import com.sports.designsystem.theme.xlargeRadius

@Composable
fun IconBox(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.errorContainer,
    iconTintColor: Color? = null,
    boxSize: Dp = 64.dp,
    iconSize: Dp = 32.dp,
    icon: ImageVector = AppIcons.Info,
) {
    Box(
        modifier =
            modifier
                .size(boxSize)
                .clip(fullRadius())
                .background(backgroundColor)
    ) {
        Image(
            modifier = Modifier.size(iconSize).align(Alignment.Center),
            imageVector = icon,
            colorFilter = iconTintColor?.let { ColorFilter.tint(it) },
            contentDescription = null,
        )
    }
}

@Composable
fun ErrorIconBox() =
    IconBox(
        backgroundColor = MaterialTheme.colorScheme.errorContainer,
        iconTintColor =
            if (!isSystemInDarkTheme()) {
                MaterialTheme.colorScheme.error
            } else {
                MaterialTheme.colorScheme.onErrorContainer
            },
        icon = AppIcons.Cancel,
    )

@Composable
fun InfoIconBox() =
    IconBox(
        backgroundColor = LocalAppColors.current.yellow100,
        iconTintColor = LocalAppColors.current.yellow400,
        icon = AppIcons.Info,
    )

@Composable
fun SuccessIconBox() =
    IconBox(
        icon = AppIcons.CheckCircle,
        iconTintColor = LocalAppColors.current.green300,
        backgroundColor = LocalAppColors.current.green100,
    )

@Composable
@Preview
private fun IconBoxPreview() {
    AppTheme {
        Row(Modifier.background(MaterialTheme.colorScheme.background).padding(20.dp)) {
            InfoIconBox()
            MediumSpacer()
            ErrorIconBox()
            MediumSpacer()
            SuccessIconBox()
        }
    }
}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun DarkIconBoxPreview() {
    AppTheme  {
        Row(Modifier.background(MaterialTheme.colorScheme.background).padding(20.dp)) {
            InfoIconBox()
            MediumSpacer()
            ErrorIconBox()
            MediumSpacer()
            SuccessIconBox()
        }
    }
}
