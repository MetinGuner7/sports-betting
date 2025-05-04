package com.sports.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .apply { if (focusRequester != null) focusRequester(focusRequester) },
            enabled = enabled,
            readOnly = readOnly,
            textStyle = textStyle,
            label = label?.let { { Text(it) } },
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
            supportingText = {
                if (isError && errorText != null) {
                    Text(
                        text = errorText,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.labelSmall
                    )
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldPreview() {
    AppTheme {
        Column(Modifier.padding(16.dp)) {
            AppTextField(
                focusRequester = FocusRequester(),
                label = "Mağaza",
                placeholder = "Filtrele",
                onValueChange = {},
                value = ""
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldDisabledPreview() {
    AppTheme {
        Column(Modifier.padding(16.dp)) {
            AppTextField(
                focusRequester = FocusRequester(),
                value = "Preview",
                onValueChange = {},
                enabled = false,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldWithTrailingPreview() {
    AppTheme {
        Column(Modifier.padding(16.dp)) {
            AppTextField(
                focusRequester = FocusRequester(),
                value = "Preview",
                onValueChange = {},
                trailingIcon = { Image(imageVector = AppIcons.Check, contentDescription = null) },
                leadingIcon = {
                    Image(
                        modifier = Modifier.size(20.dp),
                        imageVector = AppIcons.Info,
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(
                            MaterialTheme.colorScheme.secondary
                        )
                    )
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun LongTextFieldPreview() {
    AppTheme {
        Column(Modifier.padding(16.dp)) {
            AppTextField(
                focusRequester = FocusRequester(),
                value = "Preview",
                modifier = Modifier.height(320.dp),
                onValueChange = {},
            )
        }
    }
}
