package com.michaelrmossman.multiplatform.discover.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.material3.Icon
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.navigation.OnBackHandler
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import co.touchlab.kermit.Logger
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.faves_none
import org.jetbrains.compose.resources.stringResource

@Composable
fun FavesScreen(
    horizontalPadding: Dp,
    listState: MainListState,
    modifier: Modifier,
    onEvent: (MainListEvent) -> Unit
) {
    // Logger.d("HEY") { "FavesScreen" }

    val lazyState = rememberLazyListState()

    OnBackHandler(
        currentDestination = listState.currentNavType,
        onEvent = onEvent,
        startDestination = listState.startDestination
    )

    when (listState.favourites.size) {
        0 -> {
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
                    text = stringResource(
                        resource = Res.string.faves_none
                    )
                )
                Icon(
                    contentDescription = null,
                    imageVector = Icons.Outlined.BookmarkAdd,
                    modifier = Modifier
                        .padding(
                            vertical = 16.dp
                        )
                        .size(40.dp)
                )
            }
        }
        else -> {
            // Logger.d("HEY") { listState.favourites.size.toString() }
            LazyColumn(
                modifier = Modifier.padding(horizontal = 6.dp),
                state = lazyState
            ) {
//                items(
//                    items = listState.favourites,
//                    key = { fave -> fave.faId }
//                ) { fave ->
//                    ListItemRoute(
//                        horizontalPadding = horizontalPadding,
//                        modifier = modifier,
//                        onClick = {
//                            onEvent(
//                                MainListEvent.SetCurrentRouteId(
//                                    fave.faId
//                                )
//                            )
//                        },
//                        fave = fave
//                    )
//                }
            }
        }
    }
}