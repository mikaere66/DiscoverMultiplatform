package com.michaelrmossman.multiplatform.discover.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.enums.Screen
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import com.michaelrmossman.multiplatform.discover.tabs.HomeTab
import com.michaelrmossman.multiplatform.discover.tabs.RouteTab
import com.michaelrmossman.multiplatform.discover.theme.AppTheme
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.random_message
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveButton
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.currentKoinScope

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun MainScreen() {
    val viewModel = koinViewModel<MainViewModel>()
    val state by viewModel.state.collectAsState()
    val spacerHeight = 8.dp
    val modifier = Modifier.padding(
        start = 16.dp,
        end = 16.dp,
        bottom = 16.dp
    )
    val horizontalPadding = 8.dp

    val routeCount = remember { mutableStateOf(0L) }
    val coroutineScope = rememberCoroutineScope()
    coroutineScope.launch {
        routeCount.value = viewModel.getRouteCount()
    }

    AppTheme {
        AdaptiveScaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (state.currentScreen.value) {
                    Screen.Home -> {
                        HomeTab(
                            onEvent = viewModel::onEvent,
                            routeCount = routeCount.value,
                            state = state
                        )
                    }
                    Screen.Faves -> {
                    }
                    Screen.Route -> {
                        RouteTab(
                            onEvent = viewModel::onEvent,
                            state = state
                        )
                    }
                    Screen.Settings -> {
                    }
                }
            }
        }
    }
}

@Composable
inline fun <reified T: ViewModel> koinViewModel(): T {
    val scope = currentKoinScope()
    return viewModel {
        scope.get<T>()
    }
}