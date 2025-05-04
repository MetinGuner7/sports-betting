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

public val AppIcons.Info: ImageVector
    get() {
        if (_info != null) {
            return _info!!
        }
        _info =
            Builder(
                name = "Info",
                defaultWidth = 29.0.dp,
                defaultHeight = 30.0.dp,
                viewportWidth = 29.0f,
                viewportHeight = 30.0f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFFF04438)),
                        strokeLineWidth = 2.28571f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(14.5f, 20.333f)
                        verticalLineTo(15.0f)
                        moveTo(14.5f, 9.667f)
                        horizontalLineTo(14.513f)
                        moveTo(27.833f, 15.0f)
                        curveTo(27.833f, 22.364f, 21.864f, 28.333f, 14.5f, 28.333f)
                        curveTo(7.136f, 28.333f, 1.167f, 22.364f, 1.167f, 15.0f)
                        curveTo(1.167f, 7.636f, 7.136f, 1.667f, 14.5f, 1.667f)
                        curveTo(21.864f, 1.667f, 27.833f, 7.636f, 27.833f, 15.0f)
                        close()
                    }
                }
                .build()
        return _info!!
    }

private var _info: ImageVector? = null

@Preview
@Composable
private fun Preview(): Unit {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Info, contentDescription = "")
    }
}
