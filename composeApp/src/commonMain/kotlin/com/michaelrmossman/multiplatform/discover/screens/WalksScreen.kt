package com.michaelrmossman.multiplatform.discover.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.components.ListItemRoute
import com.michaelrmossman.multiplatform.discover.navigation.OnBackHandler
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveCircularProgressIndicator
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun WalksScreen(
    horizontalPadding: Dp,
    listState: MainListState,
    modifier: Modifier,
    onEvent: (MainListEvent) -> Unit
) {
    val lazyListState = rememberLazyListState()

    OnBackHandler(
        currentDestination = listState.currentNavType,
        onEvent = onEvent,
        startDestination = listState.startDestination
    )

    // Logger.d("HEY") { listState.routes.size.toString() }
    when (listState.pleaseWaitMessage.isNotBlank()) {
        true -> {
            Column(
                horizontalAlignment =
                    Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    ),
                    text = listState.pleaseWaitMessage
                )
                AdaptiveCircularProgressIndicator(
                    modifier = Modifier.padding(
                        vertical = 16.dp
                    )
                )
            }
        }
        else -> {
            LazyColumn(
                modifier = Modifier.padding(horizontal = 6.dp),
                state = lazyListState
            ) {
                items(
                    items = listState.routes,
                    key = { route -> route.roId }
                ) { route ->
                    ListItemRoute(
                        horizontalPadding = horizontalPadding,
                        modifier = modifier,
                        onClick = {
                            onEvent(
                                MainListEvent.SetCurrentRouteId(
                                    route.roId
                                )
                            )
                        },
                        route = route
                    )
                }
            }
        }
    }
}