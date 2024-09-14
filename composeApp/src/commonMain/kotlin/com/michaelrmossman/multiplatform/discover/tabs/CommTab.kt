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
import com.michaelrmossman.multiplatform.discover.components.ListItemComm
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import com.michaelrmossman.multiplatform.discover.utils.Constants.communityIcons

@Composable
fun CommTab(
    viewModel: MainViewModel
) {
    val lazyListState = rememberLazyListState()
    val listState by viewModel.state.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadCommunityItems() }

    // Logger.d("HEY") { listState.communityItems.size.toString() }
    LazyColumn(
        modifier = Modifier.padding(horizontal = 6.dp),
        state = lazyListState
    ) {
        items(
            items = listState.communityItems,
            key = { item -> item.ciId }
        ) { item ->
            val imageVector = communityIcons[item.ciId.toInt().minus(1)]

            ListItemComm(
                imageVector = imageVector,
                item = item,
                onClick = {
//                    viewModel.onEvent(
//                        MainListEvent.SetCommunityItemId(
//                            item.ciId
//                        )
//                    )
                }
            )
        }
    }
}