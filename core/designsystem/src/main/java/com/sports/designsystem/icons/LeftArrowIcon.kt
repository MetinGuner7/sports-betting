package com.sports.designsystem.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Round as strokeCapRound
import androidx.compose.ui.graphics.StrokeJoin.Companion.Round as strokeJoinRound
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val AppIcons.LeftArrowIcon: ImageVector
    get() {
        if (_leftArrowIcon != null) {
            return _leftArrowIcon!!
        }
        _leftArrowIcon =
            Builder(
                name = "LeftArrowIcon",
                defaultWidth = 24.0.dp,
                defaultHeight = 24.0.dp,
                viewportWidth = 24.0f,
                viewportHeight = 24.0f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF181D27)),
                        strokeLineWidth = 2.0f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(20.0f, 12.0f)
                        horizontalLineTo(4.0f)
                        moveTo(4.0f, 12.0f)
                        lineTo(10.0f, 18.0f)
                        moveTo(4.0f, 12.0f)
                        lineTo(10.0f, 6.0f)
                    }
                }
                .build()
        return _leftArrowIcon!!
    }

private var _leftArrowIcon: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.LeftArrowIcon, contentDescription = null)
    }
}
