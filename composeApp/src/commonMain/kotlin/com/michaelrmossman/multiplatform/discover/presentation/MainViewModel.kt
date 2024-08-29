package com.michaelrmossman.multiplatform.discover.presentation

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
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.coroutineScope
import com.rickclephas.kmp.observableviewmodel.stateIn
import discovermultiplatform.composeapp.generated.resources.loading_distance
import discovermultiplatform.composeapp.generated.resources.loading_message
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.subtitle_walks
import kotlinx.coroutines.delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.getString

class MainViewModel(
    val database: DatabaseImpl
) : ViewModel() {

    @NativeCoroutines
    var _communityItems = MutableStateFlow<List<CommunityItems>>(
        viewModelScope, value = listOf()
    )

    @NativeCoroutines
    var _currentNavType = MutableStateFlow(
        viewModelScope, value = NavigationType.CommunityScreen
    )

    @NativeCoroutines
    var _pleaseWaitMessage = MutableStateFlow(
        viewModelScope, value = String()
    )

    @NativeCoroutines
    var _routeItems = MutableStateFlow<List<Routes>>(
        viewModelScope, value = listOf()
    )

    private var _sortRoutesByDistance = false

    @NativeCoroutines
    var _transitItems = MutableStateFlow<List<TransitItems>>(
        viewModelScope, value = listOf()
    )

    @NativeCoroutinesState
    val _state = MutableStateFlow(
        viewModelScope, value = MainListState()
    )
    @NativeCoroutinesState
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

    @NativeCoroutines
    suspend fun getCoordsCount(): Long {
        return database.getCoordsCount()
    }

    @NativeCoroutines
    suspend fun getRouteById(roId: Long): Routes {
        return database.getRouteById(roId)
    }

    @NativeCoroutines
    suspend fun getRouteKtById(roId: Long): RouteKt {
        return database.getRouteKtById(roId)
    }

    @NativeCoroutines
    suspend fun getRouteCount(): Long {
        return database.getRouteCount()
    }

    @NativeCoroutines
    suspend fun getRoutes(): List<Routes> {
        return database.getRoutes(
            byDistance = _sortRoutesByDistance,
            connectors = false
        )
    }

    // @NativeCoroutines
    init {
        viewModelScope.coroutineScope.launch(Dispatchers.IO) {
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

    @NativeCoroutines
    suspend fun loadCommunityItems() {

        if (_communityItems.value.isEmpty()) {

            if (database.getCommunityCount() == 0L) {
                database.loadCommunityItems()
            }

            _communityItems.value = database.getCommunityItems()
        }
    }

    @NativeCoroutines
    suspend fun loadRoutes() {

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

    @NativeCoroutines
    suspend fun loadRouteDistances() {

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

    @NativeCoroutines
    suspend fun loadTransitItems() {

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
                viewModelScope.coroutineScope.launch {
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
                viewModelScope.coroutineScope.launch {
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
                viewModelScope.coroutineScope.launch {
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
                                        currentRouteKt = MutableStateFlow<RouteKt?>(
                                            viewModelScope, currentRoute
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
                                        currentRouteKt = MutableStateFlow<RouteKt?>(
                                            viewModelScope, currentRoute
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
                viewModelScope.coroutineScope.launch {
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