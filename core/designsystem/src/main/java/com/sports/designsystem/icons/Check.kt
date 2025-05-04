package com.sports.designsystem.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val AppIcons.Check: ImageVector
    get() {
        if (_Check != null) {
            return _Check!!
        }
        _Check =
            ImageVector.Builder(
                name = "Check",
                defaultWidth = 12.dp,
                defaultHeight = 12.dp,
                viewportWidth = 12f,
                viewportHeight = 12f,
            )
                .apply {
                    path(
                        fill = null,
                        fillAlpha = 1.0f,
                        stroke = SolidColor(Color(0xFFF9FAFB)),
                        strokeAlpha = 1.0f,
                        strokeLineWidth = 1.6666f,
                        strokeLineCap = StrokeCap.Round,
                        strokeLineJoin = StrokeJoin.Round,
                        strokeLineMiter = 1.0f,
                        pathFillType = PathFillType.NonZero,
                    ) {
                        moveTo(10f, 3f)
                        lineTo(4.5f, 8.5f)
                        lineTo(2f, 6f)
                    }
                }
                .build()
        return _Check!!
    }

private var _Check: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.Check, contentDescription = "")
    }
}
