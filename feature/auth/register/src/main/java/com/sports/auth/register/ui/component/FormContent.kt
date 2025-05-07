package com.sports.auth.register.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sports.auth.register.ui.RegisterEvent
import com.sports.auth.register.ui.RegisterViewState
import com.sports.designsystem.component.AppText
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.XXXLargeSpacer
import com.sports.designsystem.theme.semibold
import com.sports.ui.component.AppButton
import com.sports.ui.component.AppTextField
import com.sports.ui.extentions.clearFocusOnKeyboardDismiss

@Composable
fun FormContent(
    uiState: RegisterViewState,
    onViewEvent: (RegisterEvent) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    LazyColumn (modifier = modifier.imePadding(),
        verticalArrangement = Arrangement.Center
    ){
        item {
            Column (
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                AppText(
                    text = "Welcome to\n" +
                            "Sports Betting\n" +
                            "family !",
                    style = MaterialTheme.typography.headlineMedium.semibold,
                    textAlign = TextAlign.Center
                )
                XXXLargeSpacer()
                AppTextField(
                    modifier = Modifier.clearFocusOnKeyboardDismiss(),
                    value = uiState.email,
                    onValueChange = {
                        if (it.length <= 50) {
                            onViewEvent.invoke(RegisterEvent.OnEmailChanged(it))
                        }
                    },
                    placeholder = "E-Mail",
                    keyboardOptions =
                        KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
                    label = "E-Mail",
                    keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
                )
                AppTextField(
                    modifier = Modifier.clearFocusOnKeyboardDismiss(),
                    value = uiState.password,
                    onValueChange = {
                        if (it.length <= 20) {
                            onViewEvent.invoke(RegisterEvent.OnPasswordChanged(it))
                        }
                    },
                    placeholder = "Şifre Gir",
                    keyboardOptions =
                        KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    label = "Şifre Gir",
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    visualTransformation = PasswordVisualTransformation(),
                )
                AppTextField(
                    modifier = Modifier.clearFocusOnKeyboardDismiss(),
                    value = uiState.confirmPassword,
                    onValueChange = {
                        if (it.length <= 20) {
                            onViewEvent.invoke(RegisterEvent.OnConfirmPasswordChanged(it))
                        }
                    },
                    placeholder = "Tekrar Şifre Gir",
                    keyboardOptions =
                        KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
                    label = "Tekrar Şifre Gir",
                    keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                    visualTransformation = PasswordVisualTransformation(),
                )
                XXXLargeSpacer()
                AppButton(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Üyel Ol",
                    onClick = {
                        onViewEvent.invoke(RegisterEvent.OnRegisterButtonClicked)
                        focusManager.clearFocus()
                    },
                )
            }
        }
    }
}

@Composable
@Preview
private fun PreviewFormContent() {
    AppTheme {
        Column(Modifier.background(MaterialTheme.colorScheme.background).padding(24.dp)) {
            FormContent(
                modifier = Modifier.fillMaxSize(),
                uiState = RegisterViewState(),
                onViewEvent = {},
                focusManager = LocalFocusManager.current,
            )
        }
    }
}
