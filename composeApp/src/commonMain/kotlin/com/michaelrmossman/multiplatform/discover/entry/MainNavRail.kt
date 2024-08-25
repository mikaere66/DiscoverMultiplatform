package com.michaelrmossman.multiplatform.discover.entry

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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

@Composable
fun MainNavRail(
    listState: MainListState,
    navController: NavHostController,
    onClick: (NavHostController, NavigationItem, NavigationType) -> Unit,
    onEvent: (MainListEvent) -> Unit,
    onNavigateUp: () -> Unit
) {
    Row(modifier = Modifier.padding(8.dp)) {

        NavigationRail(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.inverseOnSurface)
                /* Simulated vertical divider */
                .offset(x = (-1).dp)
        ) {
            // Push buttons to bottom of navRail
            Spacer(modifier = Modifier.weight(1F))

            listState.navItems.forEach { navItem ->

                NavigationRailItem(
                    icon = {
                        NavBarIcon(
                            navItem = navItem
                        )
                    },
                    label = { Text(navItem.label) },
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

        Scaffold(
            modifier = Modifier.weight(1F),
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