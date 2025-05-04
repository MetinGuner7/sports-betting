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

val AppIcons.User: ImageVector
    get() {
        if (_user != null) {
            return _user!!
        }
        _user =
            Builder(
                name = "User",
                defaultWidth = 40.0.dp,
                defaultHeight = 40.0.dp,
                viewportWidth = 25.0f,
                viewportHeight = 24.0f,
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
                        moveTo(12.125f, 15.0f)
                        curveTo(8.955f, 15.0f, 6.136f, 16.531f, 4.341f, 18.906f)
                        curveTo(3.955f, 19.417f, 3.761f, 19.673f, 3.768f, 20.018f)
                        curveTo(3.773f, 20.285f, 3.94f, 20.622f, 4.15f, 20.787f)
                        curveTo(4.422f, 21.0f, 4.799f, 21.0f, 5.552f, 21.0f)
                        horizontalLineTo(18.698f)
                        curveTo(19.451f, 21.0f, 19.828f, 21.0f, 20.1f, 20.787f)
                        curveTo(20.309f, 20.622f, 20.477f, 20.285f, 20.482f, 20.018f)
                        curveTo(20.488f, 19.673f, 20.295f, 19.417f, 19.909f, 18.906f)
                        curveTo(18.114f, 16.531f, 15.295f, 15.0f, 12.125f, 15.0f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF717680)),
                        strokeLineWidth = 1.5f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(12.125f, 12.0f)
                        curveTo(14.61f, 12.0f, 16.625f, 9.985f, 16.625f, 7.5f)
                        curveTo(16.625f, 5.015f, 14.61f, 3.0f, 12.125f, 3.0f)
                        curveTo(9.64f, 3.0f, 7.625f, 5.015f, 7.625f, 7.5f)
                        curveTo(7.625f, 9.985f, 9.64f, 12.0f, 12.125f, 12.0f)
                        close()
                    }
                }
                .build()
        return _user!!
    }

private var _user: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.User, contentDescription = null)
    }
}
