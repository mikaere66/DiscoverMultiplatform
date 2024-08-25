package com.michaelrmossman.multiplatform.discover.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsWalk
import androidx.compose.material.icons.filled.ModeOfTravel
import androidx.compose.material.icons.filled.Bookmarks
import androidx.compose.material.icons.filled.Hiking
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Bookmarks
import androidx.compose.material.icons.outlined.ModeOfTravel
import androidx.compose.material.icons.outlined.Home
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.bar_community
import discovermultiplatform.composeapp.generated.resources.bar_faves
import discovermultiplatform.composeapp.generated.resources.bar_cycling
import discovermultiplatform.composeapp.generated.resources.bar_walks
import org.jetbrains.compose.resources.getString

object NavigationList {

    suspend fun getBottomNavItems(
        currentDestination: NavigationType
    ) : List<NavigationItem> {
        return listOf(
            NavigationItem(
                label = getString(Res.string.bar_community),
                navType = NavigationType.CommunityScreen,
                route = NavigationType.CommunityScreen.name,
                selectedIcon = Icons.Filled.Home,
                unselectedIcon = Icons.Outlined.Home
//                icon = when (iSiOS) {
//                    true -> CupertinoIcons.Outlined.Building2
//                    else -> Icons.Outlined.LocationCity
//                }
            ),
            NavigationItem(
                label = getString(Res.string.bar_faves),
                navType = NavigationType.FavesScreen,
                route = NavigationType.FavesScreen.name,
                selectedIcon = Icons.Filled.Bookmarks,
                unselectedIcon = Icons.Outlined.Bookmarks
//                icon = when (iSiOS) {
//                    true -> CupertinoIcons.Outlined.Bicycle
//                    else -> Icons.Outlined.PedalBike
//                }
            ),
            NavigationItem(
                label = getString(Res.string.bar_cycling),
                navType = NavigationType.TransitScreen,
                route = NavigationType.TransitScreen.name,
                selectedIcon = Icons.Filled.ModeOfTravel,
                unselectedIcon = Icons.Outlined.ModeOfTravel
//                icon = when (iSiOS) {
//                    true -> CupertinoIcons.Outlined.Bicycle
//                    else -> Icons.Outlined.PedalBike
//                }
            ),
            NavigationItem(
                label = getString(Res.string.bar_walks),
                navType = NavigationType.WalksScreen,
                route = NavigationType.WalksScreen.name,
                selectedIcon = Icons.Filled.Hiking,
                unselectedIcon = Icons.AutoMirrored.Outlined.DirectionsWalk
//                icon = when (iSiOS) {
//                    true -> CupertinoIcons.Outlined.FigureWalk
//                    else -> Icons.AutoMirrored.Outlined.DirectionsWalk
//                }
            )
        // Update boolean "selected" based on current nav destination id
        )
        .onEach { navItem ->
            navItem.selected = (
                currentDestination.name
                ==
                navItem.navType.name
            )
        }
    }
}