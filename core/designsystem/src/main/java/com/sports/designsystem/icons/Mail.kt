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

val AppIcons.Mail: ImageVector
    get() {
        if (_mail != null) {
            return _mail!!
        }
        _mail =
            Builder(
                name = "Mail",
                defaultWidth = 20.0.dp,
                defaultHeight = 20.0.dp,
                viewportWidth = 20.0f,
                viewportHeight = 20.0f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF717680)),
                        strokeLineWidth = 1.5f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(17.917f, 15.0f)
                        lineTo(12.381f, 10.0f)
                        moveTo(7.619f, 10.0f)
                        lineTo(2.083f, 15.0f)
                        moveTo(1.667f, 5.833f)
                        lineTo(8.471f, 10.596f)
                        curveTo(9.022f, 10.982f, 9.297f, 11.175f, 9.597f, 11.25f)
                        curveTo(9.862f, 11.315f, 10.138f, 11.315f, 10.403f, 11.25f)
                        curveTo(10.703f, 11.175f, 10.978f, 10.982f, 11.529f, 10.596f)
                        lineTo(18.333f, 5.833f)
                        moveTo(5.667f, 16.667f)
                        horizontalLineTo(14.333f)
                        curveTo(15.733f, 16.667f, 16.434f, 16.667f, 16.968f, 16.394f)
                        curveTo(17.439f, 16.154f, 17.821f, 15.772f, 18.061f, 15.302f)
                        curveTo(18.333f, 14.767f, 18.333f, 14.067f, 18.333f, 12.667f)
                        verticalLineTo(7.333f)
                        curveTo(18.333f, 5.933f, 18.333f, 5.233f, 18.061f, 4.698f)
                        curveTo(17.821f, 4.228f, 17.439f, 3.846f, 16.968f, 3.606f)
                        curveTo(16.434f, 3.333f, 15.733f, 3.333f, 14.333f, 3.333f)
                        horizontalLineTo(5.667f)
                        curveTo(4.267f, 3.333f, 3.566f, 3.333f, 3.032f, 3.606f)
                        curveTo(2.561f, 3.846f, 2.179f, 4.228f, 1.939f, 4.698f)
                        curveTo(1.667f, 5.233f, 1.667f, 5.933f, 1.667f, 7.333f)
                        verticalLineTo(12.667f)
                        curveTo(1.667f, 14.067f, 1.667f, 14.767f, 1.939f, 15.302f)
                        curveTo(2.179f, 15.772f, 2.561f, 16.154f, 3.032f, 16.394f)
                        curveTo(3.566f, 16.667f, 4.267f, 16.667f, 5.667f, 16.667f)
                        close()
                    }
                }
                .build()
        return _mail!!
    }

private var _mail: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Mail, contentDescription = null)
    }
}
