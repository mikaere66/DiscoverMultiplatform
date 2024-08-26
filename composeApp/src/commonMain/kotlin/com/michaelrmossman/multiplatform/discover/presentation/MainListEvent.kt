package com.michaelrmossman.multiplatform.discover.presentation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.navigation.NavigationType

sealed interface MainListEvent {

//    data object DeleteAllFavourites: MainListEvent

    data class SetCommunityItemId(val itemId : Long): MainListEvent

    data class SetCurrentNavType(val navType: NavigationType): MainListEvent

    // data class SetCurrentRoute(val route : Routes?) : MainListEvent
    data class SetCurrentRouteId(val routeId : Long) : MainListEvent
    // data class SetCurrentRouteKtId(val routeId: Long): MainListEvent

    data class SetTransitItemId(val itemId : Long): MainListEvent

    data class SetWindowWidthSizeClass(val widthSizeClass: WindowWidthSizeClass): MainListEvent

    data class ToggleFaveRoute(val roId: Long, val time: String?): MainListEvent

    data object ToggleSortRoutesByDistance: MainListEvent
}