package com.sports.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.Check
import com.sports.designsystem.icons.Info
import com.sports.designsystem.theme.AppTheme

@Composable
fun AppTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    errorText: String? = null,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    labelTextStyle: TextStyle = MaterialTheme.typography.labelMedium,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = true,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = OutlinedTextFieldDefaults.shape,
    focusRequester: FocusRequester? = null,
    colors: TextFieldColors = OutlinedTextFieldDefaults.colors(),
    minHeight: Dp = 48.dp,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = minHeight)
            .then(if (focusRequester != null) Modifier.focusRequester(focusRequester) else Modifier),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label?.let { { Text(it, style = labelTextStyle) } },
        placeholder = placeholder?.let { { Text(it) } },
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        interactionSource = interactionSource,
        shape = shape,
        colors = colors,
        supportingText = if (isError && errorText != null) {
            {
                Text(
                    text = errorText,
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.labelSmall
                )
            }
        } else {
            null
        }
    )
}


@Preview(showBackground = true, name = "TextField - Normal (Küçültülmüş)")
@Composable
private fun TextFieldPreview() {
    AppTheme {
        Column(Modifier.padding(16.dp)) {
            AppTextField(
                label = "Etiket",
                placeholder = "Placeholder",
                onValueChange = {},
                value = "",
                 minHeight = 40.dp
            )
        }
    }
}

@Preview(showBackground = true, name = "TextField - Değerli (Küçültülmüş)")
@Composable
private fun TextFieldWithValuePreview() {
    AppTheme {
        Column(Modifier.padding(16.dp)) {
            AppTextField(
                label = "Etiket",
                value = "Dolu Değer",
                onValueChange = {},
                minHeight = 40.dp
            )
        }
    }
}


@Preview(showBackground = true, name = "TextField - İkonlu (Küçültülmüş)")
@Composable
private fun TextFieldWithIconsPreview() {
    AppTheme {
        Column(Modifier.padding(16.dp)) {
            AppTextField(
                label = "İkonlu Alan",
                value = "Preview",
                onValueChange = {},
                trailingIcon = { Image(imageVector = AppIcons.Check, contentDescription = null) },
                leadingIcon = {
                    Image(
                        modifier = Modifier.size(20.dp),
                        imageVector = AppIcons.Info,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
                    )
                },
                minHeight = 48.dp
            )
        }
    }
}

@Preview(showBackground = true, name = "TextField - Hatalı (Küçültülmüş)")
@Composable
private fun TextFieldErrorPreview() {
    AppTheme {
        Column(Modifier.padding(16.dp)) {
            AppTextField(
                label = "Hatalı Alan",
                value = "Yanlış Değer",
                onValueChange = {},
                isError = true,
                errorText = "Bu alan hatalı!",
                minHeight = 48.dp
            )
        }
    }
}
