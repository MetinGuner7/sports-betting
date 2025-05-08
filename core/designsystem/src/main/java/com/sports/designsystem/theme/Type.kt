package com.sports.designsystem.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sports.designsystem.R

// These values are only for demonstration purposes.
// They should be replaced with the actual values from the design system.
private val roboto =
    FontFamily(
        Font(R.font.roboto_regular, FontWeight.Medium),
        Font(R.font.roboto_regular, FontWeight.Normal),
        Font(R.font.roboto_regular, FontWeight.SemiBold),
    )


// Extension properties for TextStyle to support different font weights
val TextStyle.regular: TextStyle
    get() = copy(fontWeight = FontWeight.Normal)

val TextStyle.medium: TextStyle
    get() = copy(fontWeight = FontWeight.Medium)

val TextStyle.semibold: TextStyle
    get() = copy(fontWeight = FontWeight.SemiBold)

// Updated Typography according to the design system
val Typography =
    Typography(
        // Display 2xl
        displayLarge =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 72.sp,
                letterSpacing = (-0.02).sp,
                lineHeight = 90.sp,
            ),
        // Display xl
        displayMedium =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 60.sp,
                letterSpacing = (-0.02).sp,
                lineHeight = 72.sp,
            ),
        // Display lg
        displaySmall =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 48.sp,
                letterSpacing = (-0.02).sp,
                lineHeight = 60.sp,
            ),
        // Display md
        headlineLarge =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 36.sp,
                letterSpacing = (-0.02).sp,
                lineHeight = 44.sp,
            ),
        // Display sm
        headlineMedium =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 30.sp,
                lineHeight = 38.sp,
            ),
        // Display xs
        headlineSmall =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 24.sp,
                lineHeight = 32.sp,
            ),
        // Text xl
        titleLarge =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp,
                lineHeight = 30.sp,
            ),
        // Text lg
        titleMedium =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 18.sp,
                lineHeight = 28.sp,
            ),
        // Text md
        bodyLarge =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                lineHeight = 20.sp,
            ),
        // Text sm
        bodyMedium =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
        // Text xs
        bodySmall =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 18.sp,
            ),
        // Rest of them undefined in design system but keep them for future use
        labelLarge =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                lineHeight = 16.sp,
            ),
        labelMedium =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
                lineHeight = 20.sp,
            ),
        labelSmall =
            TextStyle(
                fontFamily = roboto,
                fontWeight = FontWeight.Medium,
                fontSize = 10.sp,
                lineHeight = 16.sp,
            ),
    )
