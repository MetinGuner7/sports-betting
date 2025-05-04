package com.sports.designsystem.icons

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.EvenOdd
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

val AppIcons.EmptyList: ImageVector
    get() {
        if (_emptyList != null) {
            return _emptyList!!
        }
        _emptyList =
            Builder(
                name = "EmptyList",
                defaultWidth = 129.0.dp,
                defaultHeight = 144.0.dp,
                viewportWidth = 129.0f,
                viewportHeight = 144.0f,
            )
                .apply {
                    path(
                        fill = SolidColor(Color(0xFFF5F5F5)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = NonZero,
                    ) {
                        moveTo(35.17f, 22.29f)
                        lineTo(96.86f, 24.97f)
                        arcTo(9.53f, 9.54f, 47.52f, false, true, 105.97f, 34.91f)
                        lineTo(101.9f, 127.48f)
                        arcTo(9.53f, 9.54f, 47.52f, false, true, 91.96f, 136.59f)
                        lineTo(30.27f, 133.91f)
                        arcTo(9.53f, 9.54f, 47.52f, false, true, 21.16f, 123.97f)
                        lineTo(25.22f, 31.4f)
                        arcTo(9.53f, 9.54f, 47.52f, false, true, 35.17f, 22.29f)
                        close()
                    }
                    path(
                        fill = SolidColor(Color(0xFFffffff)),
                        stroke = null,
                        strokeLineWidth = 0.0f,
                        strokeLineCap = Butt,
                        strokeLineJoin = Miter,
                        strokeLineMiter = 4.0f,
                        pathFillType = EvenOdd,
                    ) {
                        moveTo(26.12f, 16.22f)
                        curveTo(20.9f, 16.9f, 17.23f, 21.69f, 17.92f, 26.91f)
                        lineTo(30.07f, 118.77f)
                        curveTo(30.76f, 123.99f, 35.55f, 127.67f, 40.77f, 126.99f)
                        lineTo(61.5f, 124.27f)
                        curveTo(61.44f, 123.85f, 61.42f, 123.43f, 61.42f, 123.0f)
                        curveTo(61.42f, 117.74f, 65.7f, 113.47f, 71.0f, 113.47f)
                        curveTo(75.87f, 113.47f, 79.89f, 117.09f, 80.5f, 121.78f)
                        lineTo(101.99f, 118.96f)
                        curveTo(107.21f, 118.28f, 110.89f, 113.49f, 110.19f, 108.27f)
                        lineTo(98.04f, 16.41f)
                        curveTo(97.35f, 11.19f, 92.56f, 7.51f, 87.34f, 8.19f)
                        lineTo(26.12f, 16.22f)
                        close()
                    }
                    group {
                        path(
                            fill = SolidColor(Color(0xFFE9EAEB)),
                            stroke = null,
                            strokeLineWidth = 0.0f,
                            strokeLineCap = Butt,
                            strokeLineJoin = Miter,
                            strokeLineMiter = 4.0f,
                            pathFillType = NonZero,
                        ) {
                            moveTo(17.92f, 26.91f)
                            lineTo(16.81f, 27.05f)
                            lineTo(17.92f, 26.91f)
                            close()
                            moveTo(26.12f, 16.22f)
                            lineTo(26.26f, 17.32f)
                            lineTo(26.12f, 16.22f)
                            close()
                            moveTo(30.07f, 118.77f)
                            lineTo(28.96f, 118.92f)
                            lineTo(30.07f, 118.77f)
                            close()
                            moveTo(40.77f, 126.99f)
                            lineTo(40.92f, 128.09f)
                            horizontalLineTo(40.92f)
                            lineTo(40.77f, 126.99f)
                            close()
                            moveTo(61.5f, 124.27f)
                            lineTo(61.65f, 125.38f)
                            lineTo(62.76f, 125.23f)
                            lineTo(62.61f, 124.12f)
                            lineTo(61.5f, 124.27f)
                            close()
                            moveTo(80.5f, 121.78f)
                            lineTo(79.39f, 121.92f)
                            lineTo(79.53f, 123.03f)
                            lineTo(80.64f, 122.89f)
                            lineTo(80.5f, 121.78f)
                            close()
                            moveTo(101.99f, 118.96f)
                            lineTo(102.14f, 120.07f)
                            lineTo(101.99f, 118.96f)
                            close()
                            moveTo(110.19f, 108.27f)
                            lineTo(109.09f, 108.42f)
                            lineTo(110.19f, 108.27f)
                            close()
                            moveTo(98.04f, 16.41f)
                            lineTo(96.94f, 16.55f)
                            lineTo(98.04f, 16.41f)
                            close()
                            moveTo(87.34f, 8.19f)
                            lineTo(87.49f, 9.3f)
                            lineTo(87.34f, 8.19f)
                            close()
                            moveTo(19.02f, 26.76f)
                            curveTo(18.41f, 22.15f, 21.66f, 17.93f, 26.26f, 17.32f)
                            lineTo(25.97f, 15.11f)
                            curveTo(20.14f, 15.87f, 16.04f, 21.22f, 16.81f, 27.05f)
                            lineTo(19.02f, 26.76f)
                            close()
                            moveTo(31.18f, 118.63f)
                            lineTo(19.02f, 26.76f)
                            lineTo(16.81f, 27.05f)
                            lineTo(28.96f, 118.92f)
                            lineTo(31.18f, 118.63f)
                            close()
                            moveTo(40.63f, 125.88f)
                            curveTo(36.02f, 126.48f, 31.78f, 123.24f, 31.18f, 118.63f)
                            lineTo(28.96f, 118.92f)
                            curveTo(29.73f, 124.75f, 35.08f, 128.86f, 40.92f, 128.09f)
                            lineTo(40.63f, 125.88f)
                            close()
                            moveTo(61.36f, 123.16f)
                            lineTo(40.63f, 125.88f)
                            lineTo(40.92f, 128.09f)
                            lineTo(61.65f, 125.38f)
                            lineTo(61.36f, 123.16f)
                            close()
                            moveTo(62.61f, 124.12f)
                            curveTo(62.56f, 123.75f, 62.53f, 123.38f, 62.53f, 123.0f)
                            horizontalLineTo(60.3f)
                            curveTo(60.3f, 123.48f, 60.33f, 123.95f, 60.39f, 124.42f)
                            lineTo(62.61f, 124.12f)
                            close()
                            moveTo(62.53f, 123.0f)
                            curveTo(62.53f, 118.36f, 66.32f, 114.58f, 71.0f, 114.58f)
                            verticalLineTo(112.35f)
                            curveTo(65.09f, 112.35f, 60.3f, 117.11f, 60.3f, 123.0f)
                            horizontalLineTo(62.53f)
                            close()
                            moveTo(71.0f, 114.58f)
                            curveTo(75.3f, 114.58f, 78.86f, 117.79f, 79.39f, 121.92f)
                            lineTo(81.6f, 121.64f)
                            curveTo(80.93f, 116.39f, 76.43f, 112.35f, 71.0f, 112.35f)
                            verticalLineTo(114.58f)
                            close()
                            moveTo(101.85f, 117.86f)
                            lineTo(80.35f, 120.67f)
                            lineTo(80.64f, 122.89f)
                            lineTo(102.14f, 120.07f)
                            lineTo(101.85f, 117.86f)
                            close()
                            moveTo(109.09f, 108.42f)
                            curveTo(109.7f, 113.03f, 106.46f, 117.25f, 101.85f, 117.86f)
                            lineTo(102.14f, 120.07f)
                            curveTo(107.97f, 119.31f, 112.07f, 113.96f, 111.3f, 108.13f)
                            lineTo(109.09f, 108.42f)
                            close()
                            moveTo(96.94f, 16.55f)
                            lineTo(109.09f, 108.42f)
                            lineTo(111.3f, 108.13f)
                            lineTo(99.15f, 16.26f)
                            lineTo(96.94f, 16.55f)
                            close()
                            moveTo(87.49f, 9.3f)
                            curveTo(92.1f, 8.7f, 96.33f, 11.94f, 96.94f, 16.55f)
                            lineTo(99.15f, 16.26f)
                            curveTo(98.38f, 10.43f, 93.03f, 6.32f, 87.2f, 7.09f)
                            lineTo(87.49f, 9.3f)
                            close()
                            moveTo(26.26f, 17.32f)
                            lineTo(87.49f, 9.3f)
                            lineTo(87.2f, 7.09f)
                            lineTo(25.97f, 15.11f)
                            lineTo(26.26f, 17.32f)
                            close()
                        }
                        path(
                            fill = SolidColor(Color(0xFFE9EAEB)),
                            stroke = null,
                            strokeLineWidth = 0.0f,
                            strokeLineCap = Butt,
                            strokeLineJoin = Miter,
                            strokeLineMiter = 4.0f,
                            pathFillType = NonZero,
                        ) {
                            moveTo(27.86f, 27.06f)
                            lineTo(87.98f, 19.18f)
                            arcTo(2.23f, 2.23f, 127.46f, false, true, 90.49f, 21.1f)
                            lineTo(90.49f, 21.1f)
                            arcTo(2.23f, 2.23f, 127.46f, false, true, 88.57f, 23.61f)
                            lineTo(28.45f, 31.48f)
                            arcTo(2.23f, 2.23f, 127.46f, false, true, 25.94f, 29.56f)
                            lineTo(25.94f, 29.56f)
                            arcTo(2.23f, 2.23f, 127.46f, false, true, 27.86f, 27.06f)
                            close()
                        }
                        path(
                            fill = SolidColor(Color(0xFFE9EAEB)),
                            stroke = null,
                            strokeLineWidth = 0.0f,
                            strokeLineCap = Butt,
                            strokeLineJoin = Miter,
                            strokeLineMiter = 4.0f,
                            pathFillType = NonZero,
                        ) {
                            moveTo(30.79f, 49.21f)
                            lineTo(90.91f, 41.33f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 93.42f, 43.26f)
                            lineTo(93.42f, 43.26f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 91.49f, 45.76f)
                            lineTo(31.38f, 53.64f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 28.87f, 51.72f)
                            lineTo(28.87f, 51.72f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 30.79f, 49.21f)
                            close()
                        }
                        path(
                            fill = SolidColor(Color(0xFFE9EAEB)),
                            stroke = null,
                            strokeLineWidth = 0.0f,
                            strokeLineCap = Butt,
                            strokeLineJoin = Miter,
                            strokeLineMiter = 4.0f,
                            pathFillType = NonZero,
                        ) {
                            moveTo(33.72f, 71.37f)
                            lineTo(93.84f, 63.49f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 96.35f, 65.41f)
                            lineTo(96.35f, 65.41f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 94.42f, 67.92f)
                            lineTo(34.31f, 75.8f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 31.8f, 73.87f)
                            lineTo(31.8f, 73.87f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 33.72f, 71.37f)
                            close()
                        }
                        path(
                            fill = SolidColor(Color(0xFFF5F5F5)),
                            stroke = null,
                            strokeLineWidth = 0.0f,
                            strokeLineCap = Butt,
                            strokeLineJoin = Miter,
                            strokeLineMiter = 4.0f,
                            pathFillType = NonZero,
                        ) {
                            moveTo(29.03f, 35.92f)
                            lineTo(47.97f, 33.44f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 50.48f, 35.36f)
                            lineTo(50.48f, 35.36f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 48.56f, 37.87f)
                            lineTo(29.62f, 40.35f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 27.11f, 38.42f)
                            lineTo(27.11f, 38.42f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 29.03f, 35.92f)
                            close()
                        }
                        path(
                            fill = SolidColor(Color(0xFFF5F5F5)),
                            stroke = null,
                            strokeLineWidth = 0.0f,
                            strokeLineCap = Butt,
                            strokeLineJoin = Miter,
                            strokeLineMiter = 4.0f,
                            pathFillType = NonZero,
                        ) {
                            moveTo(30.86f, 58.22f)
                            lineTo(52.01f, 55.45f)
                            arcTo(1.12f, 1.12f, 127.41f, false, true, 53.27f, 56.41f)
                            lineTo(53.27f, 56.41f)
                            arcTo(1.12f, 1.12f, 127.41f, false, true, 52.3f, 57.66f)
                            lineTo(31.15f, 60.43f)
                            arcTo(1.12f, 1.12f, 127.41f, false, true, 29.9f, 59.47f)
                            lineTo(29.9f, 59.47f)
                            arcTo(1.12f, 1.12f, 127.41f, false, true, 30.86f, 58.22f)
                            close()
                        }
                        path(
                            fill = SolidColor(Color(0xFFF5F5F5)),
                            stroke = null,
                            strokeLineWidth = 0.0f,
                            strokeLineCap = Butt,
                            strokeLineJoin = Miter,
                            strokeLineMiter = 4.0f,
                            pathFillType = NonZero,
                        ) {
                            moveTo(34.89f, 80.23f)
                            lineTo(53.83f, 77.75f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 56.34f, 79.67f)
                            lineTo(56.34f, 79.67f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 54.42f, 82.18f)
                            lineTo(35.48f, 84.66f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 32.97f, 82.73f)
                            lineTo(32.97f, 82.73f)
                            arcTo(2.23f, 2.24f, 127.51f, false, true, 34.89f, 80.23f)
                            close()
                        }
                    }
                }
                .build()
        return _emptyList!!
    }

private var _emptyList: ImageVector? = null

@Preview
@Composable
private fun Preview() {
    Box(modifier = Modifier.padding(12.dp)) {
        Image(imageVector = AppIcons.EmptyList, contentDescription = "")
    }
}
