package com.michaelrmossman.multiplatform.discover.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.components.ListItemTransit
import com.michaelrmossman.multiplatform.discover.navigation.OnBackHandler
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import com.michaelrmossman.multiplatform.discover.utils.Constants.transitIcons

@Composable
fun TransitScreen(
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

    // Logger.d("HEY") { listState.transitItems.size.toString() }
    LazyColumn(
        modifier = Modifier.padding(horizontal = 6.dp),
        state = lazyListState
    ) {
        items(
            items = listState.transitItems,
            key = { item -> item.trId }
        ) { item ->
            val imageVector = transitIcons[item.trId.toInt().minus(1)]

            ListItemTransit(
                horizontalPadding = horizontalPadding,
                imageVector = imageVector,
                item = item,
                modifier = modifier,
                onClick = {
                    onEvent(
                        MainListEvent.SetTransitItemId(
                            item.trId
                        )
                    )
                }
            )
        }
    }
}