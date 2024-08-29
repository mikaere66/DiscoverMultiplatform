package com.michaelrmossman.multiplatform.discover.presentation

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.michaelrmossman.multiplatform.discover.database.CommunityItems
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.database.TransitItems
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.navigation.NavigationItem
import com.michaelrmossman.multiplatform.discover.navigation.NavigationType
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import kotlinx.coroutines.flow.MutableStateFlow

data class MainListState(

    val communityItems: List<CommunityItems> = emptyList(),

    val currentNavType: NavigationType = NavigationType.CommunityScreen,

    val currentRoute: Routes? = null,
    @NativeCoroutines
    val currentRouteKt: MutableStateFlow<RouteKt?>? = null,

    val favourites: List<Any> = emptyList(),

    val navItems: List<NavigationItem> = emptyList(),

    val pleaseWaitMessage: String = String(),

    val routes: List<Routes> = emptyList(),

    val sortRoutesByDistance: Boolean = false,

    val startDestination: NavigationType = currentNavType,

    val transitItems: List<TransitItems> = emptyList(),

    var windowWidthSizeClass: WindowWidthSizeClass =
        WindowWidthSizeClass.Compact
)
