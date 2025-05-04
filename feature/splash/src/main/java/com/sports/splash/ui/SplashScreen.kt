package com.sports.splash.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sports.designsystem.theme.AppTheme
import com.sports.ui.extentions.ObserveAsEvents

@Composable
fun SplashRoute(
    navigateToHome: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel(),
) {
    ObserveAsEvents(viewModel.event) { event ->
        when (event) {
            is SplashEvent.NavigateToHome -> {
                navigateToHome()
            }
            is SplashEvent.NavigateToLogin -> navigateToLogin()
        }
    }
    SplashScreen()
}

@Composable
fun SplashScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize()) {

    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun SplashScreenPreview() {
    AppTheme  { SplashScreen() }
}
