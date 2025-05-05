package com.sports.login.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
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
import com.sports.designsystem.component.MainText
import com.sports.designsystem.theme.AppTheme
import com.sports.designsystem.theme.XLargeSpacer
import com.sports.designsystem.theme.XXXLargeSpacer
import com.sports.designsystem.theme.semibold
import com.sports.login.ui.LoginEvent
import com.sports.login.ui.LoginViewState
import com.sports.ui.component.AppButton
import com.sports.ui.component.AppTextField
import com.sports.ui.extentions.clearFocusOnKeyboardDismiss

@Composable
fun FormContent(
    uiState: LoginViewState,
    onViewEvent: (LoginEvent) -> Unit,
    focusManager: FocusManager,
    modifier: Modifier = Modifier,
) {
    Column (
        modifier = modifier.imePadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        XXXLargeSpacer()
        MainText(
            text = "Welcome to\n" +
                    "Sports Betting\n" +
                    "family!",
            style = MaterialTheme.typography.headlineMedium.semibold,
            textAlign = TextAlign.Center
        )
        XXXLargeSpacer()
        XXXLargeSpacer()
        AppTextField(
            modifier = Modifier.clearFocusOnKeyboardDismiss(),
            value = uiState.username,
            onValueChange = {
                if (it.length <= 50) {
                    onViewEvent.invoke(LoginEvent.OnUsernameChanged(it))
                }
            },
            placeholder = "E-Mail",
            keyboardOptions =
                KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next),
            label = "E-Mail",
            keyboardActions = KeyboardActions(onNext = { focusManager.moveFocus(FocusDirection.Down) }),
        )

        XXXLargeSpacer()

        AppTextField(
            modifier = Modifier.clearFocusOnKeyboardDismiss(),
            value = uiState.password,
            onValueChange = {
                if (it.length <= 20) {
                    onViewEvent.invoke(LoginEvent.OnPasswordChanged(it))
                }
            },
            placeholder = "Şifre Gir",
            keyboardOptions =
                KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done),
            label = "Şifre",
            keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
            visualTransformation = PasswordVisualTransformation(),
        )
        Spacer(modifier = Modifier.Companion.weight(1f))

        AppButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Login",
            enabled = uiState.username.isNotEmpty() && uiState.password.isNotEmpty(),
            onClick = {
                onViewEvent.invoke(LoginEvent.OnButtonClicked)
                focusManager.clearFocus()
            },
        )
        XLargeSpacer()
    }
}

@Composable
@Preview
private fun PreviewFormContent() {
    AppTheme {
        Column(Modifier.background(MaterialTheme.colorScheme.background).padding(24.dp)) {
            FormContent(
                uiState = LoginViewState(),
                onViewEvent = {},
                focusManager = LocalFocusManager.current,
            )
        }
    }
}
