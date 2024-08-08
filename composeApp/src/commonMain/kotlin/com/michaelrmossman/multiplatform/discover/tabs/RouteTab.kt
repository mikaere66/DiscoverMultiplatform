package com.michaelrmossman.multiplatform.discover.tabs

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.draw.clip
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
import kotlinx.coroutines.launch
import org.koin.compose.currentKoinScope

@Composable
fun RouteTab(
    onEvent: (MainListEvent) -> Unit,
    state: MainScreenState
) {
    val locationShape = RoundedCornerShape(10.dp)
    val modifier = Modifier
        .padding(horizontal = 12.dp)
        .clip(locationShape)
        .border(1.dp, Color.Gray, locationShape)
        .fillMaxWidth()
        .height(200.dp)
    val scrollState = rememberScrollState()
    val verticalScrollEnableState = remember { mutableStateOf(true) }

    state.currentRoute?.let { route ->
        val coords = Coordinates(
            route.lati, route.long
        )
        // route.name?.let { name -> Logger.i("HEY") { name } }

        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(
                        scrollState,
                        enabled = verticalScrollEnableState.value
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = route.name)
                Spacer(Modifier.height(50.dp))
                LocationVisualizer(
                    coords = coords,
                    modifier = modifier,
                    parentScrollEnableState = verticalScrollEnableState,
                    title = route.name
                )
            }
        }
    }
}