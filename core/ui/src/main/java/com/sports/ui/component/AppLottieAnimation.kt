package com.sports.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.sports.ui.extentions.getResourceIdFromName

@Composable
fun AppLottieAnimation(
    lottieUrl: String,
    lottieResourceType: AppLottieResourceType,
    modifier: Modifier = Modifier,
    lottieModifier: Modifier = Modifier,
    visibility: Boolean = true,
    speed: Float = 1F,
    contentScale: ContentScale = ContentScale.Fit,
    iterations: Int = LottieConstants.IterateForever,
    onLottieFinish: () -> Unit = {},
) {
    if (visibility) {
        val context = LocalContext.current

        val composition by
        rememberLottieComposition(
            when (lottieResourceType) {
                AppLottieResourceType.RAW -> {
                    val rawSource = context.getResourceIdFromName(lottieUrl, "raw")
                    LottieCompositionSpec.RawRes(rawSource)
                }
                AppLottieResourceType.URL -> LottieCompositionSpec.Url(lottieUrl)
            }
        )
        val progress by animateLottieCompositionAsState(composition = composition)

        Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            LottieAnimation(
                composition,
                modifier = lottieModifier.fillMaxWidth(),
                restartOnPlay = true,
                alignment = Alignment.Center,
                iterations = iterations,
                speed = speed,
                contentScale = contentScale,
            )
            if (progress == 1.0f) {
                onLottieFinish.invoke()
            }
        }
    }
}

enum class AppLottieResourceType {
    RAW,
    URL,
}

@Preview(showBackground = true)
@Composable
private fun LoodosLottieAnimationPreview() {
    AppLottieAnimation(
        lottieUrl = "loading.json",
        lottieResourceType = AppLottieResourceType.RAW,
        onLottieFinish = {},
    )
}
