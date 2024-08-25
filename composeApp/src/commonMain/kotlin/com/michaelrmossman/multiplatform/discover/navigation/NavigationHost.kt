package com.michaelrmossman.multiplatform.discover.navigation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.components.AppSubtitle
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import com.michaelrmossman.multiplatform.discover.screens.TransitScreen
import com.michaelrmossman.multiplatform.discover.screens.FavesScreen
import com.michaelrmossman.multiplatform.discover.screens.CommunityScreen
import com.michaelrmossman.multiplatform.discover.screens.WalksScreen

@Composable
fun NavigationHost(
    listState: MainListState,
    navController: NavHostController,
    onEvent: (MainListEvent) -> Unit,
    paddingValues: PaddingValues
) {
    val columnModifier = when (listState.windowWidthSizeClass) {
        /* Phones in Portrait orientation have NavBar at bottom */
        WindowWidthSizeClass.Compact -> Modifier.fillMaxWidth()
        /* Phones in Landscape orientation have NavRail at left.
           Or, large-screen devices have full NavDrawer at left */
        else -> Modifier.fillMaxHeight()
    }
    val horizontalPadding = 16.dp
    val itemsModifier = Modifier.padding(4.dp)

    Column(
        /* Append the modifier initialised above */
        modifier = columnModifier.padding(paddingValues)
    ) {
        AppSubtitle(
            listState = listState
        )

        NavHost(
            navController = navController,
            startDestination = listState.startDestination.name
        ) {
            NavigationType.entries.forEach { navEnum ->
                when (navEnum.name) {
                    NavigationType.CommunityScreen.name -> {
                        composable(route = navEnum.name) {
                            CommunityScreen(
                                horizontalPadding = horizontalPadding,
                                listState = listState,
                                modifier = itemsModifier,
                                onEvent = onEvent
                            )
                        }
                    }
                    NavigationType.FavesScreen.name -> {
                        composable(route = navEnum.name) {
                            FavesScreen(
                                horizontalPadding = horizontalPadding,
                                listState = listState,
                                modifier = itemsModifier,
                                onEvent = onEvent
                            )
                        }
                    }
                    NavigationType.TransitScreen.name -> {
                        composable(route = navEnum.name) {
                            TransitScreen(
                                horizontalPadding = horizontalPadding,
                                listState = listState,
                                modifier = itemsModifier,
                                onEvent = onEvent
                            )
                        }
                    }
                    NavigationType.WalksScreen.name -> {
                        composable(route = navEnum.name) {
                            WalksScreen(
                                horizontalPadding = horizontalPadding,
                                listState = listState,
                                modifier = itemsModifier,
                                onEvent = onEvent
                            )
                        }
                    }
                }
            }
        }
    }
}