package com.michaelrmossman.multiplatform.discover.actions

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Sort
import androidx.compose.material.icons.outlined.SortByAlpha
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import com.michaelrmossman.multiplatform.discover.presentation.MainListState
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.desc_main_menu
import discovermultiplatform.composeapp.generated.resources.desc_sort_by_dist
import discovermultiplatform.composeapp.generated.resources.desc_sort_by_name
import discovermultiplatform.composeapp.generated.resources.menu_random_route
import io.github.alexzhirkevich.cupertino.adaptive.icons.AdaptiveIcons
import io.github.alexzhirkevich.cupertino.adaptive.icons.MoreVert
import org.jetbrains.compose.resources.stringResource

@Composable
fun ActionsWalks(
    onEvent: (MainListEvent) -> Unit,
    listState: MainListState
) {
    var showOverflowMenu: Boolean by remember {
        mutableStateOf(false)
    }

    IconButton(
        onClick = {
            onEvent(
                MainListEvent.ToggleSortRoutesByDistance
            )
        }
    ) {
        Icon(
            contentDescription = stringResource(
                when (listState.sortRoutesByDistance) {
                    true -> Res.string.desc_sort_by_name
                    else -> Res.string.desc_sort_by_dist
                }
            ),
            imageVector = when (listState.sortRoutesByDistance) {
                true -> Icons.Outlined.SortByAlpha
                else -> Icons.AutoMirrored.Outlined.Sort
            }
        )
    }

    IconButton(
        onClick = { showOverflowMenu = !showOverflowMenu }
    ) {
        Icon(
            contentDescription = stringResource(
                Res.string.desc_main_menu
            ),
            imageVector = AdaptiveIcons.Outlined.MoreVert
        )
    }

    DropdownMenu(
        expanded = showOverflowMenu,
        onDismissRequest = { showOverflowMenu = false }
    ) {
        DropdownMenuItem(
            modifier = Modifier.padding(4.dp),
            onClick = {
                showOverflowMenu = false // Close menu
                onEvent(
                    MainListEvent.SetCurrentRouteId(
                        routeId = -1L // Random Route
                        // routeId = 68L // Huntsbury
                        // routeId = 111L // Janet Stewart
                        // routeId = 115 // Crater Rim
                        // routeId = 127L // Awaroa-Godley
                        // routeId = 137L // Bowenvale
                    )
                )
//                onEvent(
//                    MainListEvent.SetCurrentScreen( // TODO
//                        screen = Screen.Route
//                    )
//                )
            },
            text = {
                Text(
                    text = stringResource(
                        Res.string.menu_random_route
                    )
                )
            },
        )
    }
}