package com.sports.ui.component

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.designsystem.component.MainButton
import com.sports.designsystem.component.MainOutlinedButton
import com.sports.designsystem.theme.AppTheme

@Composable
fun AppButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
) {
    MainButton(
        onClick = onClick,
        text = text,
        textColor =
            if (enabled) MaterialTheme.colorScheme.onPrimary
            else MaterialTheme.colorScheme.tertiary,
        icon = icon,
        modifier = modifier,
        enabled = enabled,
    )
}

@Composable
fun AppOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    enabled: Boolean = true,
) {
    MainOutlinedButton(
        onClick = onClick,
        text = text,
        border =
            if (enabled)
                BorderStroke(
                    width = 1.dp,
                    color = ButtonDefaults.outlinedButtonColors().contentColor,
                )
            else null,
        colors =
            ButtonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = ButtonDefaults.buttonColors().contentColor,
                disabledContainerColor = ButtonDefaults.buttonColors().disabledContainerColor,
                disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
            ),
        textColor =
            if (enabled) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.tertiary,
        icon = icon,
        modifier = modifier,
        enabled = enabled,
    )
}

@Composable
fun AppBorderlessButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    textColor: Color = MaterialTheme.colorScheme.secondary,
    enabled: Boolean = true,
) {
    MainOutlinedButton(
        onClick = onClick,
        text = text,
        border = null,
        colors =
            ButtonColors(
                containerColor = Color.Transparent,
                contentColor = ButtonDefaults.buttonColors().contentColor,
                disabledContainerColor = Color.Transparent,
                disabledContentColor = ButtonDefaults.buttonColors().disabledContentColor,
            ),
        textColor = if (enabled) textColor else MaterialTheme.colorScheme.tertiary,
        icon = icon,
        modifier = modifier,
        enabled = enabled,
    )
}

@Preview
@Composable
private fun AppButtonPreview() {
    AppTheme {
        Column(Modifier.background(Color.White).padding(32.dp)) {
            AppButton(text = "Button", onClick = {})
            Spacer(modifier = Modifier.height(8.dp))
            AppButton(text = "Button", onClick = {}, enabled = false)
            Spacer(modifier = Modifier.height(8.dp))
            AppOutlinedButton(text = "Button", onClick = {})
            Spacer(modifier = Modifier.height(8.dp))
            AppOutlinedButton(text = "Button", onClick = {}, enabled = false)
            Spacer(modifier = Modifier.height(8.dp))
            AppBorderlessButton(text = "Button", onClick = {})
            Spacer(modifier = Modifier.height(8.dp))
            AppBorderlessButton(text = "Button", onClick = {}, enabled = false)
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DarkAppButtonPreview() {
    AppTheme {
        Column(Modifier.padding(32.dp)) {
            AppButton(text = "Button", onClick = {})
            Spacer(modifier = Modifier.height(8.dp))
            AppButton(text = "Button", onClick = {}, enabled = false)
            Spacer(modifier = Modifier.height(8.dp))
            AppOutlinedButton(text = "Button", onClick = {})
            Spacer(modifier = Modifier.height(8.dp))
            AppOutlinedButton(text = "Button", onClick = {}, enabled = false)
            Spacer(modifier = Modifier.height(8.dp))
            AppBorderlessButton(text = "Button", onClick = {})
            Spacer(modifier = Modifier.height(8.dp))
            AppBorderlessButton(text = "Button", onClick = {}, enabled = false)
        }
    }
}
