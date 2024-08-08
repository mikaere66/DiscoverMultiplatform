package com.michaelrmossman.multiplatform.discover.presentation

import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.entities.points.highlights.Highlight
import com.michaelrmossman.multiplatform.discover.enums.Screen

sealed interface MainListEvent {

    data object DeleteAllFavourites: MainListEvent

    data class SetCurrentHighlight(val hilt: Highlight?) : MainListEvent

    // data class SetCurrentRoute(val route : Routes?) : MainListEvent
    data class SetCurrentRouteId(val routeId : Long) : MainListEvent

    data class SetCurrentSeason(val seasonDbId : Int): MainListEvent

    data class SetCurrentScreen(val screen : Screen) : MainListEvent

    data class SetShouldSaveSeason(val save: Boolean): MainListEvent

    data class ToggleFave(val fave: Boolean, val id: Int): MainListEvent
}