package com.michaelrmossman.multiplatform.discover.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import co.touchlab.kermit.Logger
import com.composegears.tiamat.koin.koinSharedTiamatViewModel
import com.composegears.tiamat.navArgsOrNull
import com.composegears.tiamat.navDestination
import com.michaelrmossman.multiplatform.discover.components.PagerItemWalk
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.navigation.NavigationType
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

data class NavArgsData(val roId: Long, val routes: List<Routes>)

val walkScreen by navDestination<NavArgsData> {

//    val viewModel = koinSharedTiamatViewModel<MainViewModel>()
//    val listState by viewModel.state.collectAsState()
    val args = navArgsOrNull()
    args?.roId?.let { currentRouteId ->
        val routes = args.routes

//    args.routes.let { routes -> // Logger.d("HEY") { it.size.toString() } }

//    Logger.d("HEY") { listState.routes.size.toString() } // TODO: 0

//@Composable
//fun WalkScreen(
//    viewModel: MainViewModel,
////    horizontalPadding: Int,
////    modifier: Modifier,
////    navController: NavHostController,
////    onMainEvent: (MainListEvent) -> Unit,
////    onWalkEvent: (WalkListEvent) -> Unit,
////    walkListState: WalkListState
//) {
//    val listState by viewModel.state.collectAsState()

    val coroutineScope = rememberCoroutineScope()
//    val currentRouteId = 1L // args?.roId ?: 0L // walkListState.currentRouteId
    val currentRoute = routes.single { route ->
        currentRouteId == route.roId
    }
    var currentRouteIndex by remember {
        mutableStateOf(
            routes.indexOf(currentRoute)
        )
    }
    val listSize = routes.size
    val onRandomClick = {
        val max = listSize.minus(1)
        currentRouteIndex = (0..max).random()
    }
    val pagerState = rememberPagerState { listSize }

    Logger.d("HEY") { currentRouteIndex.toString() }

    /* Used for BOTH initial position AND random */
    LaunchedEffect(currentRouteIndex) {
        setPagerIndex(
            coroutineScope = coroutineScope,
            index = currentRouteIndex,
            // onWalkEvent = onWalkEvent,
            pagerState = pagerState,
            // walkListState = walkListState
        )
        // Logger.d("HEY") { "LaunchedEffect $currentRouteIndex" }
    }

//    LaunchedEffect(pagerState) {
//        // https://developer.android.com/develop/ui/compose/layouts/pager#get-notified
//        snapshotFlow { pagerState.currentPage }.collect { index ->
//            if (
//                walkListState.currentRouteId
//                !=
//                walkListState.routes[index].roId
//            ) {
////                val roId = walkListState.routes[index].roId
////                onWalkEvent(
////                    WalkListEvent.SetCurrentRouteId(
////                        roId = roId
////                    )
////                )
//                // Logger.d("HEY") { "snapshotFlow $roId" }
//            }
//        }
//    }

    HorizontalPager(
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 8.dp
        ),
        pageSpacing = 16.dp,
        state = pagerState
    ) { index ->

        Card(
            modifier = Modifier.clip(shape = Shapes().medium)
        ) {

            PagerItemWalk(
//                horizontalPadding = horizontalPadding,
                itemIndex = index,
//                modifier = Modifier.height(
//                    componentHeight * (1 - pageOffset)
//                ),
                onRandomClick = onRandomClick,
//                onWalkEvent = onWalkEvent,
                route = routes[index]
            )
        }
    }
}
}
//}

private fun setPagerIndex(
    coroutineScope: CoroutineScope,
    index: Int,
    // onWalkEvent: (WalkListEvent) -> Unit,
    pagerState: PagerState,
    // walkListState: WalkListState
) {
    if (index != -1) {
        coroutineScope.launch {
//            if (
//                walkListState.currentRouteId
//                !=
//                walkListState.routes[index].roId
//            ) {
//                onWalkEvent(
//                    WalkListEvent.SetCurrentRouteId(
//                        walkListState.routes[index].roId
//                    )
//                )
//                Logger.d("HEY") { "setPagerIndex $index" }
//            }
            pagerState.scrollToPage(index)
        }
    }
}