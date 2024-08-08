package com.michaelrmossman.multiplatform.discover.tabs

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
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainScreenState
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import com.michaelrmossman.multiplatform.discover.screens.koinViewModel
import com.michaelrmossman.multiplatform.discover.theme.AppTheme
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.random_button
import discovermultiplatform.composeapp.generated.resources.random_message
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveButton
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.currentKoinScope

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun HomeTab(
    onEvent: (MainListEvent) -> Unit,
    routeCount: Long,
    state: MainScreenState
) {
    val message = stringResource(
        resource = Res.string.random_message,
        formatArgs = arrayOf(routeCount)
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = message)

        AdaptiveButton(
            onClick = {
                onEvent(
                    MainListEvent.SetCurrentRouteId(
                        routeId = -1 // Random
                    )
                )
                onEvent(
                    MainListEvent.SetCurrentScreen(
                        screen = Screen.Route
                    )
                )
            }
        ) {
            Text(
                stringResource(
                    resource = Res.string.random_button
                )
            )
        }
    }
}