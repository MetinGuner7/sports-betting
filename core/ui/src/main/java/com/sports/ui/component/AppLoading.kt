package com.sports.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sports.designsystem.theme.AppTheme

private const val LOTTIE_RAW_URL = "loading"

private const val EXIT_ANIMATION_DURATION = 50

@Composable
fun AppLoading(
    isDisplayed: Boolean,
    modifier: Modifier = Modifier,
    dismissOnBackPress: Boolean = true,
) {
    AnimatedVisibility(
        visible = isDisplayed,
        modifier = modifier,
        exit =
            shrinkOut(animationSpec = keyframes { durationMillis = EXIT_ANIMATION_DURATION }) +
                    fadeOut(animationSpec = keyframes { durationMillis = EXIT_ANIMATION_DURATION }),
    ) {
        Dialog(
            properties = DialogProperties(dismissOnBackPress = dismissOnBackPress),
            onDismissRequest = {},
        ) {
            Box(modifier = Modifier.size(60.dp)) {
                AppLottieAnimation(
                    lottieUrl = LOTTIE_RAW_URL,
                    lottieResourceType = AppLottieResourceType.RAW,
                    modifier = Modifier.fillMaxWidth(),
                    lottieModifier = Modifier,
                    visibility = true,
                )
            }
        }
    }
}

@Preview
@Composable
private fun BodyPreview() {
    AppTheme  { AppLoading(isDisplayed = true) }
}
