package com.sports.auth.register.ui

import androidx.compose.foundation.layout.fillMaxSize
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
import com.sports.auth.register.ui.component.FormContent
import com.sports.designsystem.icons.AppIcons
import com.sports.designsystem.icons.LeftArrowIcon
import com.sports.designsystem.theme.AppTheme
import com.sports.ui.component.AppCenterTopAppBar
import com.sports.ui.component.AppLoading
import com.sports.ui.extentions.ObserveAsEvents
import com.sports.ui.extentions.TrackScreenViewEvent

@Composable
fun RegisterRoute(
    navigateBack: () -> Unit,
    navigateLogin: () -> Unit,
    navigateToHome: () -> Unit,
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            is RegisterEvent.NavigateBack -> navigateBack()
            is RegisterEvent.NavigateToLogin -> navigateLogin()
            is RegisterEvent.NavigateToHome -> {
                navigateToHome()
            }

        }
    }

    RegisterScreen(uiState = uiState, onViewEvent = viewModel::onHandleViewEvent)
}

@Composable
fun RegisterScreen(
    uiState: RegisterViewState,
    onViewEvent: (RegisterEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    TrackScreenViewEvent(screenName = "Register")
    AppLoading(isDisplayed = uiState.loading)

    RegisterContent(modifier = modifier, uiState = uiState, onViewEvent = onViewEvent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterContent(
    uiState: RegisterViewState,
    onViewEvent: (RegisterEvent) -> Unit,
    modifier: Modifier = Modifier,
) {

    val focusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
            AppCenterTopAppBar(
                title = "Register",
                navigationIcon = AppIcons.LeftArrowIcon,
                onNavigationClick = {onViewEvent(RegisterEvent.NavigateBack)}
            )
        }
    ) { padding ->
        FormContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            uiState = uiState,
            onViewEvent = onViewEvent,
            focusManager = focusManager,
        )
    }

}

@Preview
@Composable
private fun RegisterPreview() {
    AppTheme { RegisterScreen(uiState = RegisterViewState(), onViewEvent = {}) }
}
