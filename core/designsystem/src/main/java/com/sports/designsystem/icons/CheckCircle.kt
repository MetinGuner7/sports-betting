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

public val AppIcons.CheckCircle: ImageVector
    get() {
        if (_checkCircle != null) {
            return _checkCircle!!
        }
        _checkCircle =
            Builder(
                name = "CheckCircle",
                defaultWidth = 33.0.dp,
                defaultHeight = 32.0.dp,
                viewportWidth = 33.0f,
                viewportHeight = 32.0f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF12B76A)),
                        strokeLineWidth = 2.0f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(10.5f, 16.0f)
                        lineTo(14.5f, 20.0f)
                        lineTo(22.5f, 12.0f)
                        moveTo(29.833f, 16.0f)
                        curveTo(29.833f, 23.364f, 23.864f, 29.333f, 16.5f, 29.333f)
                        curveTo(9.136f, 29.333f, 3.167f, 23.364f, 3.167f, 16.0f)
                        curveTo(3.167f, 8.636f, 9.136f, 2.667f, 16.5f, 2.667f)
                        curveTo(23.864f, 2.667f, 29.833f, 8.636f, 29.833f, 16.0f)
                        close()
                    }
                }
                .build()
        return _checkCircle!!
    }

private var _checkCircle: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.CheckCircle, contentDescription = null)
    }
}
