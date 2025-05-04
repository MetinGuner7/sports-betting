package com.sports.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.User
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.LargeSpacer
import com.sports.designsystem.theme.MediumSpacer
import com.sports.designsystem.theme.radius
import com.sports.designsystem.theme.semibold
import kotlinx.coroutines.delay


@Composable
fun LoodosButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String? = null,
    icon: ImageVector? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge.semibold,
    border: BorderStroke? = null,
    shape: Shape = RoundedCornerShape(MaterialTheme.radius.medium),
    enabled: Boolean = true,
    elevation: ButtonElevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
    textColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    var clickEnabled by remember { mutableStateOf(enabled) }

    LaunchedEffect(clickEnabled) {
        if (clickEnabled) return@LaunchedEffect else delay(CLICK_DELAY)
        clickEnabled = true
    }

    Button(
        onClick = {
            if (!clickEnabled) return@Button
            clickEnabled = false
            onClick.invoke()
        },
        modifier = modifier.height(48.dp),
        enabled = enabled,
        colors = colors,
        shape = shape,
        border = border,
        elevation = elevation,
        content = {
            icon?.let {
                Icon(imageVector = icon, contentDescription = "")
                LargeSpacer()
            }

            text?.let { safeText ->
                Text(
                    text = safeText,
                    style = textStyle,
                    color = textColor,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                )
            }
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun BodyPreview() {

    AppTheme {
        Box(Modifier.background(Color.White).padding(32.dp)) {
            LoodosButton(
                enabled = true,
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                text = "Loodos Button",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BodyIconPreview() {

    AppTheme {
        Box(Modifier.background(Color.White).padding(32.dp)) {
            LoodosButton(
                enabled = true,
                icon = AppIcons.User,
                modifier = Modifier.fillMaxWidth(),
                onClick = {},
                text = "Loodos Button",
            )
        }
    }
}

private const val CLICK_DELAY = 300L
