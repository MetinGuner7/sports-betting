package com.sports.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

// Gray scale
val gray50: Color = Color(0xFFFAFAFA)
val gray100: Color = Color(0xFFF5F5F5)
val gray200: Color = Color(0xFFE9EAEB)
val gray300: Color = Color(0xFFD5D7DA)
val gray400: Color = Color(0xFFA4A7AE)
val gray500: Color = Color(0xFF717680)
val gray600: Color = Color(0xFF535862)
val gray700: Color = Color(0xFF414651)
val gray800: Color = Color(0xFF252B37)
val gray900: Color = Color(0xFF181D27)

// Error colors
val red100: Color = Color(0xFFFEF3F2)
val red200: Color = Color(0xFFFDDBD9)
val red300: Color = Color(0xFFF04438)
val red400: Color = Color(0xFFB42318)

// Basic colors
val white: Color = Color(0xFFFFFFFF)
val black: Color = Color(0xFF000000)

val Primary50 = Color(0xFFEDF0FF)
val Primary100 = Color(0xFFDEE4FF)
val Primary200 = Color(0xFFC4CDFF)
val Primary300 = Color(0xFFA0ACFF)
val Primary400 = Color(0xFF7A7FFF)
val Primary500 = Color(0xFF4D48F9)
val Primary600 = Color(0xFF4F3CEF)
val Primary700 = Color(0xFF432FD3)
val Primary800 = Color(0xFF3729AA)
val Primary900 = Color(0xFF312986)
val Primary950 = Color(0xFF1E184E)

// Light Theme
val primaryLight = Primary800
val backgroundLight = gray50
val surfaceLight = white
val onSurfaceLight = gray900
val onBackgroundLight = gray900
val outlineLight = gray300
val outlineVariantLight = gray400
val errorLight = red300
val onErrorLight = white
val errorContainerLight = red100
val onErrorContainerLight = red400
val primaryContainerLight = gray100
val onPrimaryContainerLight = gray900
val secondaryLight = gray500
val onSecondaryLight = white
val secondaryContainerLight = Primary100
val onSecondaryContainerLight = gray900
val tertiaryLight = gray400
val onTertiaryLight = white
val tertiaryContainerLight = gray100
val onTertiaryContainerLight = gray800
val surfaceVariantLight = gray100
val onSurfaceVariantLight = gray500
val onPrimaryLight = white
val scrimLight: Color = Color(0xFF000000).copy(alpha = 0.25f)
val inverseSurfaceLight = gray800
val inverseOnSurfaceLight = gray100
val inversePrimaryLight = primaryLight.copy(alpha = 0.8f)
val surfaceDimLight = gray100
val surfaceBrightLight = white
val surfaceContainerLowestLight = white
val surfaceContainerLowLight = gray50
val surfaceContainerLight = gray100
val surfaceContainerHighLight = gray200
val surfaceContainerHighestLight = white

// Dark Theme
// Note: For dark theme, we'd typically invert many of these relationships
val primaryDark = Color(0xFF1A237E)
val backgroundDark = gray900
val surfaceDark = gray800
val onSurfaceDark = gray100
val onBackgroundDark = gray100
val outlineDark = gray600
val outlineVariantDark = gray700
val errorDark = red300
val onErrorDark = red100
val errorContainerDark = red400
val onErrorContainerDark = red100
val primaryContainerDark = gray800
val onPrimaryContainerDark = gray100
val secondaryDark = gray400
val onSecondaryDark = gray900
val secondaryContainerDark = gray700
val onSecondaryContainerDark = gray100
val tertiaryDark = gray600
val onTertiaryDark = gray100
val tertiaryContainerDark = gray800
val onTertiaryContainerDark = gray200
val surfaceVariantDark = gray800
val onSurfaceVariantDark = gray300
val onPrimaryDark = white
val scrimDark: Color = Color(0xFF000000).copy(alpha = 0.25f)
val inverseSurfaceDark = gray200
val inverseOnSurfaceDark = gray800
val inversePrimaryDark = primaryDark.copy(alpha = 0.8f)
val surfaceDimDark = gray900
val surfaceBrightDark = gray800
val surfaceContainerLowestDark = black
val surfaceContainerLowDark = gray900
val surfaceContainerDark = gray800
val surfaceContainerHighDark = gray700
val surfaceContainerHighestDark = gray800

@Immutable
data class AppColors(
    // Yellow colors
    val yellow100: Color = Color(0xFFFFFAEB),
    val yellow200: Color = Color(0xFFFFF3D1),
    val yellow300: Color = Color(0xFFFFDD80),
    val yellow400: Color = Color(0xFFF79009),
    val yellow500: Color = Color(0xFFB54708),

    // Green colors
    val green100: Color = Color(0xFFECFDF3),
    val green200: Color = Color(0xFFD5FBE4),
    val green300: Color = Color(0xFF12B76A),
    val green400: Color = Color(0xFF027A48),
)

val LocalAppColors = compositionLocalOf { AppColors() }
