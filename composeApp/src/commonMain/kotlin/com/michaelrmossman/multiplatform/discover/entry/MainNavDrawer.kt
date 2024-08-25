package com.michaelrmossman.multiplatform.discover.entry

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Badge
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import com.michaelrmossman.multiplatform.discover.navigation.NavigationHost
import com.michaelrmossman.multiplatform.discover.navigation.NavigationItem
import com.michaelrmossman.multiplatform.discover.navigation.NavigationType
import com.michaelrmossman.multiplatform.discover.components.AppTopBar
import com.michaelrmossman.multiplatform.discover.components.NavBarIcon
import io.github.alexzhirkevich.cupertino.adaptive.AdaptiveScaffold
import io.github.alexzhirkevich.cupertino.adaptive.ExperimentalAdaptiveApi

@OptIn(ExperimentalAdaptiveApi::class)
@Composable
fun MainNavDrawer(
    listState: MainListState,
    navController: NavHostController,
    onClick: (NavHostController, NavigationItem, NavigationType) -> Unit,
    onEvent: (MainListEvent) -> Unit,
    onNavigateUp: () -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {

        PermanentNavigationDrawer(

            drawerContent = {

                PermanentDrawerSheet {

                    Column(
                        horizontalAlignment =
                            Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxSize()
                    ) {

                        // Push buttons to bottom of navDrawer
                        Spacer(modifier = Modifier.weight(1F))

                        listState.navItems.forEach { navItem ->
                            NavigationDrawerItem(
//                                badge = {
//                                    if (
//                                        navItem.route
//                                        ==
//                                        NavigationType.FavouritesScreen.name
//                                    ) {
//                                        if (listState.favesCount != 0) {
//                                            when (listState.favesCount) {
//                                                -1 -> Badge()
//                                                else -> Badge {
//                                                    val count =
//                                                        listState.favesCount
//                                                    Text(
//                                                        text = count.toString()
//                                                    )
//                                                }
//                                            }
//                                        }
//                                    }
//                                },
                                icon = { NavBarIcon(navItem = navItem) },
                                label = { Text(text = navItem.label) },
                                modifier = Modifier.padding(
                                    NavigationDrawerItemDefaults.ItemPadding
                                ),
                                onClick = {
                                    onClick(navController, navItem, listState.startDestination)
                                    onEvent(
                                        MainListEvent.SetCurrentNavType(
                                            navType = navItem.navType
                                        )
                                    )
                                },
                                selected = navItem.selected
                            )
                        }
                    }
                }
            }
        ) {
            AdaptiveScaffold(
                topBar = {
                    AppTopBar(
                        listState = listState,
                        onEvent = onEvent,
                        onNavigateUp = { onNavigateUp() }
                    )
                }
            ) { paddingValues ->

                NavigationHost(
                    listState = listState,
                    navController = navController,
                    onEvent = onEvent,
                    paddingValues = paddingValues
                )
            }
        }
    }
}