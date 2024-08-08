package com.michaelrmossman.multiplatform.discover.presentation

import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.entities.points.highlights.Highlight
import com.michaelrmossman.multiplatform.discover.enums.Screen
import kotlinx.coroutines.flow.MutableStateFlow

data class MainScreenState(

    val currentHighlight: Highlight? = null,

    val currentRoute: Routes? = null,

    val currentSeason: Int = 0, // All seasons

    val currentScreen: MutableStateFlow<Screen> =
        MutableStateFlow(Screen.Home),

    val favourites: List<Highlight> = emptyList(),
    val highlights: List<Highlight> = emptyList(),

    val saveAndRestoreSeason: Boolean = false,
    // val seasons: List<Seasons> = emptyList(),

    val shouldShowBottomSheet: MutableStateFlow<Boolean> =
        MutableStateFlow(false)
)