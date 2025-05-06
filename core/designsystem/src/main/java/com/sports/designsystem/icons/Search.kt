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

val AppIcons.Search: ImageVector
    get() {
        if (_search != null) {
            return _search!!
        }
        _search =
            Builder(
                name = "Search",
                defaultWidth = 20.0.dp,
                defaultHeight = 20.0.dp,
                viewportWidth = 20.0f,
                viewportHeight = 20.0f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color(0x00000000)),
                        stroke = SolidColor(Color(0xFF717680)),
                        strokeLineWidth = 1.8f,
                        strokeLineCap = strokeCapRound,
                        strokeLineJoin = strokeJoinRound,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(19.0f, 19.0f)
                        lineTo(14.65f, 14.65f)
                        moveTo(17.0f, 9.0f)
                        curveTo(17.0f, 13.418f, 13.418f, 17.0f, 9.0f, 17.0f)
                        curveTo(4.582f, 17.0f, 1.0f, 13.418f, 1.0f, 9.0f)
                        curveTo(1.0f, 4.582f, 4.582f, 1.0f, 9.0f, 1.0f)
                        curveTo(13.418f, 1.0f, 17.0f, 4.582f, 17.0f, 9.0f)
                        close()
                    }
                }
                .build()
        return _search!!
    }

private var _search: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Search, contentDescription = null)
    }
}
