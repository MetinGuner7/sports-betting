package com.sports.designsystem.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Spacing(
    val small: Dp = 4.dp,
    val medium: Dp = 8.dp,
    val large: Dp = 12.dp,
    val xlarge: Dp = 16.dp,
    val xxlarge: Dp = 20.dp,
    val xxxlarge: Dp = 24.dp,
)

val LocalSpacing = staticCompositionLocalOf { Spacing() }

// Add extension property
val MaterialTheme.spacing: Spacing
    @Composable @ReadOnlyComposable get() = LocalSpacing.current

/** Adds a [Spacer] with size of 4.dp */
@Composable fun SmallSpacer() = Spacer(modifier = Modifier.size(MaterialTheme.spacing.small))

/** Adds a [Spacer] with size of 8.dp */
@Composable fun MediumSpacer() = Spacer(modifier = Modifier.size(MaterialTheme.spacing.medium))

/** Adds a [Spacer] with size of 12.dp */
@Composable fun LargeSpacer() = Spacer(modifier = Modifier.size(MaterialTheme.spacing.large))

/** Adds a [Spacer] with size of 16.dp */
@Composable fun XLargeSpacer() = Spacer(modifier = Modifier.size(MaterialTheme.spacing.xlarge))

/** Adds a [Spacer] with size of 20.dp */
@Composable fun XXLargeSpacer() = Spacer(modifier = Modifier.size(MaterialTheme.spacing.xxlarge))

/** Adds a [Spacer] with size of 24.dp */
@Composable fun XXXLargeSpacer() = Spacer(modifier = Modifier.size(MaterialTheme.spacing.xxxlarge))
