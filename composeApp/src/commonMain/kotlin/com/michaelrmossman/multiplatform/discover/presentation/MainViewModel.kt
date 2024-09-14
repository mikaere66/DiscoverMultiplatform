package com.michaelrmossman.multiplatform.discover.presentation

import co.touchlab.kermit.Logger
import com.composegears.tiamat.TiamatViewModel
import com.michaelrmossman.multiplatform.discover.database.CommunityItems
import com.michaelrmossman.multiplatform.discover.database.DatabaseImpl
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.database.TransitItems
import com.michaelrmossman.multiplatform.discover.navigation.NavigationType
import com.michaelrmossman.multiplatform.discover.utils.FlowUtils.combine
import com.michaelrmossman.multiplatform.discover.utils.getLocalTime
import com.michaelrmossman.multiplatform.discover.utils.getTrimmedString
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.loading_message
import discovermultiplatform.composeapp.generated.resources.subtitle_walks
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.jetbrains.compose.resources.getString

class MainViewModel(
    private val database: DatabaseImpl
) : TiamatViewModel() {

    private var _communityItems: MutableStateFlow<List<CommunityItems>> =
        MutableStateFlow(value = listOf())

    private var _currentNavType = MutableStateFlow(
        value = NavigationType.CommunityScreen
    )

    private var _pleaseWaitMessage = MutableStateFlow(String())

    private var _routeItems: MutableStateFlow<List<Routes>> =
        MutableStateFlow(value = listOf())

    private var _sortRoutesByDistance = false // TODO

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
            pleaseWaitMessage = message,
            routes = routes,
            transitItems = tItems
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(
        5000L
    ) , MainListState())

    suspend fun getCoordsCount(): Long {
        return database.getCoordsCount()
    }

    suspend fun getRouteById(roId: Long): Routes {
        return database.getRouteById(roId)
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

    suspend fun loadCommunityItems() {

        if (_communityItems.value.isEmpty()) {

            if (database.getCommunityCount() == 0L) {
                database.loadCommunityItems()
            }

            _communityItems.value = database.getCommunityItems()
        }
    }

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
                Logger.i("TIMER END") { getLocalTime(false) }
            }
            _pleaseWaitMessage.value = String()

            _routeItems.value = database.getRoutes(
                byDistance = _sortRoutesByDistance,
                connectors = false
            )
        }
    }

    suspend fun loadTransitItems() {

        if (_transitItems.value.isEmpty()) {

            if (database.getTransitCount() == 0L) {
                database.loadTransitItems()
            }

            _transitItems.value = database.getTransitItems()
        }
    }
}