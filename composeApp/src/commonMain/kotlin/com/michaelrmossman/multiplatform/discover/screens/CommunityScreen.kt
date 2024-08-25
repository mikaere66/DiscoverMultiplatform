package com.michaelrmossman.multiplatform.discover.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.components.ListItemCommunity
import com.michaelrmossman.multiplatform.discover.navigation.OnBackHandler
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import com.michaelrmossman.multiplatform.discover.utils.Constants.communityIcons

@Composable
fun CommunityScreen(
    horizontalPadding: Dp,
    listState: MainListState,
    modifier: Modifier,
    onEvent: (MainListEvent) -> Unit
) {
    // Logger.d("HEY") { "CommunityScreen" }

    val lazyState = rememberLazyListState()

    OnBackHandler(
        currentDestination = listState.currentNavType,
        onEvent = onEvent,
        startDestination = listState.startDestination
    )

    // Logger.d("HEY") { listState.communityItems.size.toString() }
    LazyColumn(
        modifier = Modifier.padding(horizontal = 6.dp),
        state = lazyState
    ) {
        items(
            items = listState.communityItems,
            key = { item -> item.ciId }
        ) { item ->
            val imageVector = communityIcons[item.ciId.toInt().minus(1)]

            ListItemCommunity(
                horizontalPadding = horizontalPadding,
                imageVector = imageVector,
                item = item,
                modifier = modifier,
                onClick = {
                    onEvent(
                        MainListEvent.SetCommunityItemId(
                            item.ciId
                        )
                    )
                }
            )
        }
    }
}