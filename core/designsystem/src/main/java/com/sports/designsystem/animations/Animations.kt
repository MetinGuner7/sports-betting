package com.sports.designsystem.animations

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith

const val ANIMATION_DURATION_MS = 500
const val FLOATING_BUTTON_ANIMATION_DURATION_MS = 100
const val SLIDE_OFFSET_Y = 300
const val SLIDE_OFFSET_X = 300
const val SLIDE_DOWN_FADE_IN_DURATION = 220
const val SLIDE_DOWN_FADE_OUT_DURATION = 90
const val SOFT_ANIMATE_DURATION = 300

val slideIn =
    fadeIn() +
            slideInVertically(
                initialOffsetY = { SLIDE_OFFSET_Y },
                animationSpec = tween(ANIMATION_DURATION_MS),
            )

val floatingButtonSlideIn =
    fadeIn() +
            slideInVertically(
                initialOffsetY = { SLIDE_OFFSET_Y },
                animationSpec = tween(FLOATING_BUTTON_ANIMATION_DURATION_MS),
            )

val slideOut = slideOutVertically() + shrinkVertically() + fadeOut()

val slideInFromBottom =
    slideInVertically(
        initialOffsetY = { SLIDE_OFFSET_Y },
        animationSpec = tween(ANIMATION_DURATION_MS),
    ) + fadeIn()

val slideOutToTop =
    slideOutVertically(
        targetOffsetY = { -SLIDE_OFFSET_Y },
        animationSpec = tween(ANIMATION_DURATION_MS),
    ) + fadeOut()

val slideInFromTop =
    slideInVertically(
        initialOffsetY = { -SLIDE_OFFSET_Y },
        animationSpec = tween(ANIMATION_DURATION_MS),
    ) + fadeIn()

val slideOutToBottom =
    slideOutVertically(
        targetOffsetY = { SLIDE_OFFSET_Y },
        animationSpec = tween(ANIMATION_DURATION_MS),
    ) + fadeOut()

val slideInFromRight =
    slideInHorizontally(
        initialOffsetX = { SLIDE_OFFSET_X },
        animationSpec = tween(ANIMATION_DURATION_MS),
    ) + fadeIn()

val slideOutToLeft =
    slideOutHorizontally(
        targetOffsetX = { -SLIDE_OFFSET_X },
        animationSpec = tween(ANIMATION_DURATION_MS),
    ) + fadeOut()

// left to right enter
val slideInFromLeft =
    slideInHorizontally(
        initialOffsetX = { -SLIDE_OFFSET_X },
        animationSpec = tween(ANIMATION_DURATION_MS),
    ) + fadeIn()

// right to left exit
val slideOutToRight =
    slideOutHorizontally(
        targetOffsetX = { SLIDE_OFFSET_X },
        animationSpec = tween(ANIMATION_DURATION_MS),
    ) + fadeOut()

val softAnimate =
    fadeIn(animationSpec = tween(durationMillis = SOFT_ANIMATE_DURATION)) togetherWith
            fadeOut(animationSpec = tween(durationMillis = SOFT_ANIMATE_DURATION))

val slideDown =
    (fadeIn(animationSpec = tween(SLIDE_DOWN_FADE_IN_DURATION)) +
            slideInVertically(animationSpec = tween(SLIDE_DOWN_FADE_IN_DURATION)))
        .togetherWith(fadeOut(animationSpec = tween(SLIDE_DOWN_FADE_OUT_DURATION)))
