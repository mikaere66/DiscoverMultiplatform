package com.michaelrmossman.multiplatform.discover.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.database.CommunityItems
import com.michaelrmossman.multiplatform.discover.database.DatabaseImpl
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.database.TransitItems
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.navigation.NavigationList.getBottomNavItems
import com.michaelrmossman.multiplatform.discover.navigation.NavigationType
import com.michaelrmossman.multiplatform.discover.utils.FlowUtils.combine
import com.michaelrmossman.multiplatform.discover.utils.getLocalDate
import com.michaelrmossman.multiplatform.discover.utils.getLocalTime
import com.michaelrmossman.multiplatform.discover.utils.getTrimmedString
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.loading_distance
import discovermultiplatform.composeapp.generated.resources.loading_message
import discovermultiplatform.composeapp.generated.resources.subtitle_walks
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

class MainViewModel(
    private val database: DatabaseImpl
) : ViewModel() {

    private var _communityItems: MutableStateFlow<List<CommunityItems>> =
        MutableStateFlow(value = listOf())

    private var _currentNavType = MutableStateFlow(
        value = NavigationType.CommunityScreen
    )

    private var _pleaseWaitMessage = MutableStateFlow(String())

    private var _routeItems: MutableStateFlow<List<Routes>> =
        MutableStateFlow(value = listOf())

    private var _sortRoutesByDistance = false

    private var _transitItems: MutableStateFlow<List<TransitItems>> =
        MutableStateFlow(value = listOf())

    private val _state = MutableStateFlow(MainListState())
    val state = combine(
        _communityItems,
        _currentNavType,
        _pleaseWaitMessage,
        _routeItems,
        _state,
        _transitItems
    ) { cItems, navType, message, routes, state, tItems ->
        state.copy(
            communityItems = cItems,
            currentNavType = navType,
            navItems = getBottomNavItems(
                _currentNavType.value
            ),
            pleaseWaitMessage = message,
            routes = routes,
            transitItems = tItems
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(
        5000L
    ) , MainListState())

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val defaultSortRoutesByDist = false
            val sortRoutesByDistance: Boolean
            when (database.getSettingsBooleanCount()) {
                0L -> {
                    database.setSettingSortByDist(
                        value = defaultSortRoutesByDist
                    )
                    sortRoutesByDistance = defaultSortRoutesByDist
                }
                else -> {
                    val settingSortByDist = database.getSettingSortByDist()
                    sortRoutesByDistance = settingSortByDist.settingVal
                }
            }
            _sortRoutesByDistance = sortRoutesByDistance
            _state.update { state ->
                state.copy(
                    sortRoutesByDistance = sortRoutesByDistance
                )
            }

            val defaultStartDest = NavigationType.CommunityScreen
            val startDestination : NavigationType
            when (database.getSettingsStringCount()) {
                0L -> {
                    val startDestValue = defaultStartDest.name
                    database.setSettingStartDest(
                        value = startDestValue
                    )
                    startDestination = defaultStartDest
                }
                else -> {
                    val settingStartDest = database.getSettingStartDest()
                    val startDestValue = settingStartDest.settingVal
                    startDestination = NavigationType.entries.find { navEnum ->
                        navEnum.name == startDestValue
                    }?: defaultStartDest
                }
            }
            onEvent(MainListEvent.SetCurrentNavType(startDestination))
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

    suspend fun getRoutes(): List<Routes> {
        return database.getRoutes(
            byDistance = _sortRoutesByDistance,
            connectors = false
        )
    }

    private suspend fun loadCommunityItems() {

        if (_communityItems.value.isEmpty()) {

            if (database.getCommunityCount() == 0L) {
                database.loadCommunityItems()
            }

            _communityItems.value = database.getCommunityItems()
        }
    }

    private suspend fun loadRoutes() {

        if (_routeItems.value.isEmpty()) {

            if (database.getRouteCount() == 0L) {
                Logger.i("TIMER START") { getLocalTime(false) }

                _pleaseWaitMessage.value = getTrimmedString(
                    resource = Res.string.loading_message,
                    formatArgs = arrayOf(
                        getString(
                            resource = Res.string.subtitle_walks
                        )
                    )
                )
                delay(1_000L) /* Let progress indicator start */
                database.loadRoutes()
            }
            _pleaseWaitMessage.value = String()

            _routeItems.value = database.getRoutes(
                byDistance = _sortRoutesByDistance,
                connectors = false
            )
        }
    }

    private suspend fun loadRouteDistances() {

        if (_sortRoutesByDistance) {

            if (database.getRouteDistToCityCount() == 0L) {

                _pleaseWaitMessage.value = getTrimmedString(
                    resource = Res.string.loading_message,
                    formatArgs = arrayOf(
                        getString(
                            resource = Res.string.loading_distance
                        )
                    )
                )
                delay(500L) /* Let progress indicator start */
                // Logger.i("TIMER START") { getLocalTime(false) }
                database.loadRouteDistances(_routeItems.value)
                // Logger.i("TIMER STOP") { getLocalTime(false) }
            }
        }
        _pleaseWaitMessage.value = String()

        _routeItems.value = database.getRoutes(
            byDistance = _sortRoutesByDistance,
            connectors = false
        )
    }

    private suspend fun loadTransitItems() {

        if (_transitItems.value.isEmpty()) {

            if (database.getTransitCount() == 0L) {
                database.loadTransitItems()
            }

            _transitItems.value = database.getTransitItems()
        }
    }

    fun onEvent(event: MainListEvent) {
        when (event) {
            is MainListEvent.SetCommunityItemId -> {
            }
            is MainListEvent.SetCurrentNavType -> {
                viewModelScope.launch {
                    when (event.navType) {
                        NavigationType.CommunityScreen -> {
                            loadCommunityItems()
                        }
                        NavigationType.FavesScreen -> {
                        }
                        NavigationType.TransitScreen -> {
                            loadTransitItems()
                        }
                        NavigationType.WalksScreen -> {
                            loadRoutes()
                        }
                    }

                    _currentNavType.value = event.navType
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
            is MainListEvent.SetTransitItemId -> {
            }
//            is MainListEvent.SetCurrentRouteKtId -> {
//                viewModelScope.launch {
//                    val currentRouteKt = when (event.routeId) {
//                        0L -> null
//                        else -> {
//                            val routeId = when (event.routeId) {
//                                -1L -> {
//                                    database.getRandomRouteId()
//                                }
//                                else -> event.routeId
//                            }
//                            database.getRouteKtById(routeId)
//                        }
//                    }
//                    _state.update { state ->
//                        state.copy(
//                            currentRouteKt = MutableStateFlow(
//                                currentRouteKt
//                            )
//                        )
//                    }
//                }
//            }
            is MainListEvent.SetWindowWidthSizeClass -> {
                val widthSizeClass = event.widthSizeClass
                _state.update { state ->
                    state.copy(
                        windowWidthSizeClass = widthSizeClass
                    )
                }
            }
            is MainListEvent.ToggleFaveRoute -> {
                viewModelScope.launch {
                    val result: Long
                    when (event.time?.isBlank()) {
                        true -> { // previously NO timestamp, add fave
                            val dateStamp = getLocalDate()
                            val timeStamp = getLocalTime(true)
                            val dateTime = "$dateStamp @ $timeStamp"
                            result = database.insertFaveRoute(
                                event.roId, dateTime
                            )
                            if (result > 0L) {
                                _state.update { state ->
                                    val currentRoute =
                                        state.currentRouteKt?.value?.apply {
                                            time = dateTime
                                        }
                                    state.copy(
                                        currentRouteKt = MutableStateFlow(
                                            currentRoute
                                        )
                                    )
                                }
                            }
                        }
                        else -> { // else, set DB timestamp to String()
                            result = database.deleteFaveRoute(event.roId)
                            if (result > 0L) {
                                _state.update { state ->
                                    val currentRoute =
                                        state.currentRouteKt?.value?.apply {
                                            time = String()
                                        }
                                    state.copy(
                                        currentRouteKt = MutableStateFlow(
                                            currentRoute
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
            is MainListEvent.ToggleSortRoutesByDistance -> {
                val sortRoutesByDistance = !_sortRoutesByDistance
                viewModelScope.launch {
                    if (database.setSettingSortByDist(
                        sortRoutesByDistance
                    ) > 0) {
                        _sortRoutesByDistance = sortRoutesByDistance

                        _state.update { state ->
                            state.copy(
                                sortRoutesByDistance = sortRoutesByDistance
                            )
                        }

                        loadRouteDistances()
                    }
                }
            }
        }
    }
}