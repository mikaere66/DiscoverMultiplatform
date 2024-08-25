package com.michaelrmossman.multiplatform.discover.entry

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import com.michaelrmossman.multiplatform.discover.navigation.NavigationHost
import com.michaelrmossman.multiplatform.discover.navigation.NavigationItem
import com.michaelrmossman.multiplatform.discover.navigation.NavigationType
import com.michaelrmossman.multiplatform.discover.components.AppTopBar
import com.michaelrmossman.multiplatform.discover.components.NavBarIcon

@Composable
fun MainNavBar(
    listState: MainListState,
    navController: NavHostController,
    onClick: (NavHostController, NavigationItem, NavigationType) -> Unit,
    onEvent: (MainListEvent) -> Unit
) {
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier.clip(
                    RoundedCornerShape(15.dp, 15.dp, 0.dp, 0.dp)
                )
            ) {
                listState.navItems.forEach { navItem ->

                    NavigationBarItem(
                        icon = {
                            NavBarIcon(
                                navItem = navItem
                            )
                        },
                        label = { Text(text = navItem.label) },
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
        },
        topBar = {
            AppTopBar(
                listState = listState,
                onEvent = onEvent,
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