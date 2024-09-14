package com.michaelrmossman.multiplatform.discover.tabs

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.components.ListItemTran
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import com.michaelrmossman.multiplatform.discover.utils.Constants.transitIcons

@Composable
fun TranTab(
    viewModel: MainViewModel
) {
    val lazyListState = rememberLazyListState()
    val listState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadTransitItems() }

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

            ListItemTran(
                imageVector = imageVector,
                item = item,
                onClick = {
//                    viewModel.onEvent(
//                        MainListEvent.SetTransitItemId(
//                            item.trId
//                        )
//                    )
                }
            )
        }
    }
}