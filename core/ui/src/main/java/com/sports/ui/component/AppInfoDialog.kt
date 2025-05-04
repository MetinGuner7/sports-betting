package com.sports.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.sports.common.model.DialogType
import com.sports.designsystem.component.MainText
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.MediumSpacer
import com.sports.designsystem.theme.XLargeSpacer
import com.sports.designsystem.theme.XXXLargeSpacer
import com.sports.designsystem.theme.semibold
import com.sports.designsystem.theme.spacing
import com.sports.ui.R

@Composable
fun AppInfoDialog(
    title: String,
    message: String,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    dialogType: DialogType = DialogType.INFO,
    buttonText: String = stringResource(id = R.string.ok),
    enabled: Boolean = true,
    negativeButtonText: String? = null,
    onPositiveClick: () -> Unit = {},
    onNegativeClick: () -> Unit = {},
) {

    Dialog(
        onDismissRequest = onDismissRequest,
        properties =
            DialogProperties(dismissOnClickOutside = false, usePlatformDefaultWidth = false),
    ) {
        Surface(
            modifier = modifier.fillMaxWidth().padding(horizontal = 24.dp),
            color = MaterialTheme.colorScheme.onSecondary,
            contentColor = MaterialTheme.colorScheme.onBackground,
            shape = MaterialTheme.shapes.large,
        ) {
            Column(modifier = Modifier) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(MaterialTheme.spacing.xlarge),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    MediumSpacer()
                    when (dialogType) {
                        DialogType.INFO -> {
                            InfoIconBox()
                        }

                        DialogType.SUCCESS -> {
                            SuccessIconBox()
                        }

                        else -> {
                            ErrorIconBox()
                        }
                    }
                    if (title.isNotBlank()) {
                        XXXLargeSpacer()
                    }

                    if (title.isNotBlank()) {
                        MainText(
                            text = title,
                            style = MaterialTheme.typography.titleMedium.semibold,
                            textAlign = TextAlign.Center,
                        )
                    }
                    if (message.isNotBlank()) {
                        XLargeSpacer()
                    }
                    MainText(
                        text = message,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                    )
                    Spacer(modifier = Modifier.height(24.dp))
                    Row {
                        if (negativeButtonText != null) {
                            AppBorderlessButton(
                                modifier = Modifier.weight(1f),
                                onClick = onNegativeClick,
                                text = negativeButtonText,
                                textColor = MaterialTheme.colorScheme.onSurface,
                            )
                        }
                        AppButton(
                            modifier = Modifier.weight(1f),
                            onClick = onPositiveClick,
                            text = buttonText,
                            enabled = enabled,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun DialogTypeInfoPreview() {
    AppTheme {
        Scaffold { padding ->
            Spacer(modifier = Modifier.padding(padding))
            AppInfoDialog(
                title = "Hatırlatma",
                dialogType = DialogType.INFO,
                buttonText = "Devam Et",
                message = "2 saatin üzerindeki seyahatlerin için en az bir mola planlamalısın.",
                onDismissRequest = {},
            )
        }
    }
}

@Preview
@Composable
private fun DialogTypeErrorPreview() {
    AppTheme {
        Scaffold { padding ->
            Spacer(modifier = Modifier.padding(padding))
            AppInfoDialog(
                title = "Hatırlatma",
                dialogType = DialogType.ERROR,
                buttonText = "Devam Et",
                message = "2 saatin üzerindeki seyahatlerin için en az bir mola planlamalısın.",
                onDismissRequest = {},
            )
        }
    }
}

@Preview
@Composable
private fun DialogTypeSuccessPreview() {
    AppTheme {
        Scaffold { padding ->
            Spacer(modifier = Modifier.padding(padding))
            AppInfoDialog(
                title = "Hatırlatma",
                dialogType = DialogType.SUCCESS,
                buttonText = "Devam Et",
                message = "2 saatin üzerindeki seyahatlerin için en az bir mola planlamalısın.",
                onDismissRequest = {},
            )
        }
    }
}

