package com.sports.login.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sports.designsystem.theme.AppTheme
import com.sports.login.ui.component.FormContent
import com.sports.ui.component.AppCenterTopAppBar
import com.sports.ui.component.AppLoading
import com.sports.ui.extentions.ObserveAsEvents
import com.sports.ui.extentions.TrackScreenViewEvent

@Composable
fun LoginRoute(
    navigateRegister: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            is LoginEvent.NavigateRegister -> navigateRegister()
            is LoginEvent.NavigateToHome -> {
                navigateToHome()
            }
        }
    }

    LoginScreen(uiState = uiState, onViewEvent = viewModel::onHandleViewEvent)
}

@Composable
fun LoginScreen(
    uiState: LoginViewState,
    onViewEvent: (LoginEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TrackScreenViewEvent(screenName = "Login")
    AppLoading(isDisplayed = uiState.loading)

    LoginContent(modifier = modifier, uiState = uiState, onViewEvent = onViewEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContent(
    uiState: LoginViewState,
    onViewEvent: (LoginEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            AppCenterTopAppBar(
                title = "Login",
            )
        }
    ) { padding ->
        FormContent(
            modifier = Modifier.padding(padding)
                .padding(16.dp),
            uiState = uiState,
            onViewEvent = onViewEvent,
            focusManager = focusManager,
        )
    }

}

@Preview
@Composable
private fun LoginPreview() {
    AppTheme { LoginScreen(uiState = LoginViewState(), onViewEvent = {}) }
}
