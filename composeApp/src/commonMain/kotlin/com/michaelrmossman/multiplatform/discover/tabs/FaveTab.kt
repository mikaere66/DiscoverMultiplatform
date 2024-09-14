package com.michaelrmossman.multiplatform.discover.tabs

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.components.ListItemWalk
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import co.touchlab.kermit.Logger
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.faves_none_1
import discovermultiplatform.composeapp.generated.resources.faves_none_2
import org.jetbrains.compose.resources.stringResource

@Composable
fun FaveTab(
    viewModel: MainViewModel
) {
    val lazyListState = rememberLazyListState()
    val listState by viewModel.state.collectAsState()

    // LaunchedEffect(Unit) { viewModel.loadFavourites() }

    // Logger.d("HEY") { listState.favourites.size.toString() }
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
                        resource = Res.string.faves_none_1
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
                Text(
                    modifier = Modifier.padding(
                        horizontal = 16.dp
                    ),
                    text = stringResource(
                        resource = Res.string.faves_none_2
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
                    items = listState.favourites, // TODO
                    key = { fave -> fave.roId }
                ) { fave ->
                    ListItemWalk(
                        item = fave,
                        onClick = {
//                            viewModel.onEvent(
//                                MainListEvent.SetCurrentRouteId(
//                                    fave.roId
//                                )
//                            )
                        }
                    )
                }
            }
        }
    }
}