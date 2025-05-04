package com.sports.designsystem.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class Radius(
    val small: Dp = 8.dp,
    val medium: Dp = 12.dp,
    val large: Dp = 16.dp,
    val xlarge: Dp = 24.dp,
    val full: Dp = 100.dp,
)

val LocalRadius = staticCompositionLocalOf { Radius() }

// Add extension property
val MaterialTheme.radius: Radius
    @Composable
    @ReadOnlyComposable
    get() = LocalRadius.current


@Composable
fun smallRadius() = RoundedCornerShape(MaterialTheme.radius.small)

@Composable
fun mediumRadius() = RoundedCornerShape(MaterialTheme.radius.medium)

@Composable
fun largeRadius() = RoundedCornerShape(MaterialTheme.radius.large)

@Composable
fun xlargeRadius() = RoundedCornerShape(MaterialTheme.radius.xlarge)

@Composable
fun fullRadius() = RoundedCornerShape(MaterialTheme.radius.full)
