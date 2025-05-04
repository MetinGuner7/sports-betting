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

val AppIcons.Cancel: ImageVector
    get() {
        if (_cancel != null) {
            return _cancel!!
        }
        _cancel =
            Builder(
                name = "Cancel",
                defaultWidth = 22.0.dp,
                defaultHeight = 22.0.dp,
                viewportWidth = 22.0f,
                viewportHeight = 22.0f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF181D27)),
                        strokeLineWidth = 1.8f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(14.0f, 8.0f)
                        lineTo(8.0f, 14.0f)
                        moveTo(8.0f, 8.0f)
                        lineTo(14.0f, 14.0f)
                        moveTo(21.0f, 11.0f)
                        curveTo(21.0f, 16.523f, 16.523f, 21.0f, 11.0f, 21.0f)
                        curveTo(5.477f, 21.0f, 1.0f, 16.523f, 1.0f, 11.0f)
                        curveTo(1.0f, 5.477f, 5.477f, 1.0f, 11.0f, 1.0f)
                        curveTo(16.523f, 1.0f, 21.0f, 5.477f, 21.0f, 11.0f)
                        close()
                    }
                }
                .build()
        return _cancel!!
    }

private var _cancel: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Cancel, contentDescription = null)
    }
}
