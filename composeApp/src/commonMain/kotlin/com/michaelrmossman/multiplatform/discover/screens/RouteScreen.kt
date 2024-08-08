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
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.enums.Screen
import com.michaelrmossman.multiplatform.discover.features.geometry.Coordinates
import com.michaelrmossman.multiplatform.discover.maps.LocationVisualizer
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainScreenState
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import com.michaelrmossman.multiplatform.discover.theme.AppTheme
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi
import kotlinx.coroutines.launch
import org.koin.compose.currentKoinScope

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun RouteScreen(
    onEvent: (MainListEvent) -> Unit,
    state: MainScreenState
) {
//    val viewModel = koinViewModel<MainViewModel>()
//    val state by viewModel.state.collectAsState()
//    val spacerHeight = 8.dp
    val modifier = Modifier.padding(
        start = 16.dp,
        end = 16.dp,
        bottom = 16.dp
    )
//    val horizontalPadding = 8.dp

//    val roId = state.currentRouteId
//    val coords: Coordinates? = remember { mutableStateOf(null) }
//    val route: Routes? = remember { mutableStateOf(null) }
//    val coroutineScope = rememberCoroutineScope()
//    coroutineScope.launch {
//        route.value = viewModel.getRouteById(roId)
//        coords.value = Coordinates(
//            route.value.lati, route.value.long
//        )
//    }

    state.currentRoute?.let { route ->
        val coords = Coordinates(
            route.lati, route.long
        )

//        AppTheme {
//            AdaptiveScaffold(
//                modifier = Modifier.fillMaxSize()
//            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LocationVisualizer(
                        coords = coords,
                        modifier = modifier,
                        title = route.name
                    )
                }
//            }
//        }
    }
}