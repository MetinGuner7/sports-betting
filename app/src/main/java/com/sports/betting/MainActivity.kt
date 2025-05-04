package com.sports.betting

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.util.Consumer
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.sports.analytics.AnalyticsHelper
import com.sports.analytics.util.LocalAnalyticsHelper
import com.sports.betting.ui.MainApp
import com.sports.betting.ui.MainAppState
import com.sports.betting.ui.rememberMainAppState
import com.sports.designsystem.theme.AppTheme
import com.sports.monitor.AppStateMonitor
import com.sports.monitor.LocalAppStateMonitor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var analyticsHelper: AnalyticsHelper
    @Inject
    lateinit var appStateMonitor: AppStateMonitor

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var appState: MainAppState

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        configureEdgeToEdgeWindow()
        var uiState: MainActivityViewState by mutableStateOf(viewModel.createInitialState())

        splashScreen.setOnExitAnimationListener { splashScreenViewProvider ->
            splashScreenViewProvider.remove()
        }

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.onEach { uiState = it }.collect()
            }
        }

        enableEdgeToEdge()
        setContent {
            appState = rememberMainAppState()

            var intent: Intent? = null
            if (this.intent.action == Intent.ACTION_VIEW) {
                intent = Intent()
                intent.data = this.intent.data
                intent.action = this.intent.action
                intent.`package` = this.intent.`package`
                this.intent = null
            }
            val newIntent by collectNewIntentAsState(intent)

            CompositionLocalProvider(
                LocalAnalyticsHelper provides analyticsHelper,
                LocalAppStateMonitor provides appStateMonitor,
                LocalLifecycleOwner provides this,
            ) {
                AppTheme() {
                    MainApp(
                        uiState = uiState,
                        newIntent = newIntent,
                        onViewEvent = viewModel::onHandleViewEvent,
                    )
                }
            }
        }
    }

    private fun configureEdgeToEdgeWindow() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    @Composable
    fun ComponentActivity.collectNewIntentAsState(intent: Intent?): State<Intent?> {
        return produceState<Intent?>(initialValue = intent) {
            val consumer = Consumer<Intent> { this.value = it }
            addOnNewIntentListener(consumer)
            awaitDispose { removeOnNewIntentListener(consumer) }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}
