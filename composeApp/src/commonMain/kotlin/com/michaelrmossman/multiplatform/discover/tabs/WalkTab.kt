package com.michaelrmossman.multiplatform.discover.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.touchlab.kermit.Logger
import com.composegears.tiamat.Navigation
import com.composegears.tiamat.NavController
import com.composegears.tiamat.navDestination
import com.composegears.tiamat.rememberNavController
import com.michaelrmossman.multiplatform.discover.components.ListItemWalk
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import com.michaelrmossman.multiplatform.discover.screens.NavArgsData
import com.michaelrmossman.multiplatform.discover.screens.walkScreen
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveCircularProgressIndicator
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun WalkTab(
    navController: NavController,
    viewModel: MainViewModel
) {
//@OptIn(ExperimentalAdaptiveApi::class)
//val walkTab by navDestination<Unit> {
//    val viewModel = koinSharedTiamatViewModel<MainViewModel>()
    val lazyListState = rememberLazyListState()
    val listState by viewModel.state.collectAsState()
//    val navController = rememberNavController(
//        key = "walkNavigation",
//        startDestination = walkScreen,
//        destinations = arrayOf(
//            walkScreen,
//        )
//    )

    LaunchedEffect(Unit) { viewModel.loadRoutes() }

//    Navigation( // TODO: no go!
//        navController = navController,
////        modifier = Modifier
////            .weight(1F)
////            .padding(8.dp)
////            .border(4.dp, MaterialTheme.colorScheme.onSurface)
////            .padding(4.dp)
//    )

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
                    ListItemWalk(
                        item = route,
                        onClick = {
                            navController.navigate(
                                walkScreen,
                                NavArgsData(
                                    route.roId,
                                    listState.routes
                                )
                            )
                        }
                    )
                }
            }
        }
    }
}