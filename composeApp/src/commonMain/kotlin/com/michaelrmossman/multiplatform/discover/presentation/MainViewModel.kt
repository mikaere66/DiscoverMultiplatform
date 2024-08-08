package com.michaelrmossman.multiplatform.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.database.DatabaseImpl
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.enums.Setting
import com.michaelrmossman.multiplatform.discover.utils.SeasonFilter
import com.michaelrmossman.multiplatform.discover.utils.getLocalTime
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel(
    private val database: DatabaseImpl
) : ViewModel() {

    private var _currentSeason = MutableStateFlow(0)

    private val _seasonFilter = SeasonFilter()

    private val _state = MutableStateFlow(MainScreenState())
    val state = combine(
        _currentSeason,
        _state
    ) { season, state ->
        state.copy(
            currentSeason = season,
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(
        5000L
    ) , MainScreenState())

    init {
        /* Highlights must come before Seasons */
        viewModelScope.launch {
            // loadHighlights()
            // loadSeasons()
            // loadSettings()

            loadRoutes()
        }
    }

    private suspend fun loadRoutes() {
        if (database.getRouteCount() < 1L) {
            Logger.i("TIMER START") { getLocalTime() }
            database.loadRoutes()
        }
    }

    suspend fun getCoordsCount(): Long {
        return database.getCoordsCount()
    }

    suspend fun getRouteById(roId: Long): Routes {
        return database.getRouteById(roId)
    }

    suspend fun getRouteKtById(roId: Long): RouteKt {
        return database.getRouteKtById(roId)
    }

    suspend fun getRouteCount(): Long {
        return database.getRouteCount()
    }

    fun onEvent(event: MainListEvent) {
        when (event) {
            is MainListEvent.DeleteAllFavourites -> {
                val faves = state.value.favourites
                val iterator = faves.iterator()
                val faveCount = faves.size
                var delCount = 0
//                viewModelScope.launch {
//                    while (iterator.hasNext()) {
//                        val result = database.deleteFavourite(
//                            iterator.next().shId
//                        )
//                        if (result > 0) {
//                            delCount = delCount.plus(1)
//                        }
//                    }
//                    if (delCount == faveCount) {
//                        _state.update { state ->
//                            state.copy(
//                                favourites = emptyList()
//                            )
//                        }
//                    }
//                }
            }
            is MainListEvent.SetCurrentHighlight -> {
                _state.update { state ->
                    state.copy(
                        currentHighlight = event.hilt,
                        shouldShowBottomSheet = MutableStateFlow(
                            event.hilt != null
                        )
                    )
                }
            }
            is MainListEvent.SetCurrentRouteId -> {
                viewModelScope.launch {
                    val currentRoute = when (event.routeId) {
                        0L -> null
                        else -> {
                            val routeId = when (event.routeId) {
                                -1L -> {
                                    database.getRandomRouteId()
                                }
                                else -> event.routeId
                            }
                            database.getRouteById(routeId)
                        }
                    }
                    _state.update { state ->
                        state.copy(
                            currentRoute = currentRoute
                        )
                    }
                }
            }
            is MainListEvent.SetCurrentSeason -> {
//                when (_state.value.saveAndRestoreSeason) {
//                    true -> {
//                        val result = database.insertSetting(
//                            Setting.CurrentSeason.name,
//                            event.seasonDbId.toString()
//                        )
//                        if (result > 0L) {
//                            _currentSeason.value = event.seasonDbId
//                        }
//                    }
//                    else -> {
//                        _currentSeason.value = event.seasonDbId
//                    }
//                }
            }
            is MainListEvent.SetCurrentScreen -> {
                _state.update { state ->
                    state.copy(
                        currentScreen = MutableStateFlow(
                            event.screen
                        )
                    )
                }
            }
            is MainListEvent.SetShouldSaveSeason -> {
//                val result = database.insertSetting(
//                    Setting.RestoreSeason.name,
//                    event.save.toString()
//                )
//                if (result > 0L) {
//                    _state.update { state ->
//                        state.copy(
//                            saveAndRestoreSeason = event.save
//                        )
//                    }
//                }
            }
            is MainListEvent.ToggleFave -> {
//                when (event.fave) {
//                    true -> {
//                        database.insertFavourite(event.id)
//                    }
//                    else -> {
//                        database.deleteFavourite(event.id)
//                    }
//                }
            }
        }
    }
}