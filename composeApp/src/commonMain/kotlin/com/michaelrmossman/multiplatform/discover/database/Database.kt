package com.michaelrmossman.multiplatform.discover.database

import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.entities.Coordinates
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.features.CommunityItemCollection
import com.michaelrmossman.multiplatform.discover.features.FeatureCollectionRoutes3
import com.michaelrmossman.multiplatform.discover.features.TransitItemCollection
import com.michaelrmossman.multiplatform.discover.utils.Constants.CONNECTOR_STRING
import com.michaelrmossman.multiplatform.discover.utils.Constants.PREFS_SORT_BY_DIST
import com.michaelrmossman.multiplatform.discover.utils.Constants.PREFS_START_SCREEN
import com.michaelrmossman.multiplatform.discover.utils.Constants.ROUTE_NAME_ANOMALY
import com.michaelrmossman.multiplatform.discover.utils.DatabaseUtils.mapRouteToRouteKt
import com.michaelrmossman.multiplatform.discover.utils.getLocalTime

internal class Database(
    databaseDriverFactory: DatabaseDriverFactory
) {

    private val database = DiscoverDatabase(
        databaseDriverFactory.createDriver()
    )
    private val communityQueries = database.communityItemsQueries
    private val coordinateQueries = database.coordinatesQueries
//    private val featureQueries = database.featuresQueries
//    private val monthQueries = database.monthsQueries
    private val routeQueries = database.routesQueries
//    private val seasonQueries = database.seasonsQueries
    private val settingsBooleanQueries = database.settingsBooleanQueries
    private val settingsStringQueries = database.settingsStringQueries
    private val transitQueries = database.transitItemsQueries

    fun deleteFaveRoute(roId: Long): Long {
        var result = 0L

        routeQueries.transaction {
            /* timeStamp must come before routeId
               to match SqlDelight signature */
            routeQueries.setFaveRoute(String(), roId)
            result = routeQueries.selectChanges().executeAsOne()
        }

        return result
    }

    internal fun getCommunityCount(): Long {
        return communityQueries.getCommunityCount().executeAsOne()
    }

    internal fun getCommunityItems(): List<CommunityItems> {
        return communityQueries.getCommunityItems().executeAsList()
    }

    internal fun getCoordsCount(): Long {
        return coordinateQueries.getCoordsCount().executeAsOne()
    }

    /* Need to allow for "" ...
       WHERE name NOT LIKE %connector (IGNORE CASE) */
    internal fun getRandomRouteId(): Long {
        return routeQueries.getRandomRouteId("%$CONNECTOR_STRING").executeAsOne()
    }

    internal fun getRouteById(roId: Long): Routes {
        return routeQueries.getRouteById(roId).executeAsOne()
    }

    internal fun getRouteKtById(roId: Long): RouteKt {
        val coordinates = coordinateQueries.getCoordsById(roId).executeAsList()
        val route = routeQueries.getRouteById(roId).executeAsOne()
        return mapRouteToRouteKt(coordinates, route)
    }

    /* WHERE name NOT LIKE %connector (IGNORE CASE) */
    internal fun getRouteCount(): Long {
        return routeQueries.getRouteCount("%$CONNECTOR_STRING").executeAsOne()
    }

    /* Check to see if distance has ALREADY been set */
    internal fun getRouteDistToCityCount(): Long {
        return routeQueries.getRouteDistToCityCount().executeAsOne()
    }

    internal fun getRoutes(
        byDistance: Boolean, connectors: Boolean
    ) : List<Routes> {
        return when (connectors) {
            true -> routeQueries.getRoutes().executeAsList()
            else -> when (byDistance) {
                true -> routeQueries.getRoutesOnlyByDistance(
                    "%$CONNECTOR_STRING"
                ).executeAsList()
                else -> routeQueries.getRoutesOnly(
                    "%$CONNECTOR_STRING"
                ).executeAsList()
            }
        }
    }

    internal fun getRoutesKt(connectors: Boolean): List<RouteKt> {
        val routesKt = mutableListOf<RouteKt>()
        val routes = when (connectors) {
            true -> routeQueries.getRoutes().executeAsList()
            else -> routeQueries.getRoutesOnly(
                "%$CONNECTOR_STRING"
            ).executeAsList()
        }
        routes.forEach { route ->
            routesKt.add(getRouteKtById(route.roId))
        }
        return routesKt
    }

    internal fun getSettingsBooleanCount(): Long {
        return settingsBooleanQueries.getSettingsBooleanCount().executeAsOne()
    }

    internal fun getSettingsStringCount(): Long {
        return settingsStringQueries.getSettingsStringCount().executeAsOne()
    }

    internal fun getSettingSortByDist(): SettingsBoolean {
        val pref = PREFS_SORT_BY_DIST
        return settingsBooleanQueries.getSettingsBoolean(pref).executeAsOne()
    }

    internal fun getSettingStartDest(): SettingsString {
        val pref = PREFS_START_SCREEN
        return settingsStringQueries.getSettingsString(pref).executeAsOne()
    }

    internal fun getTransitCount(): Long {
        return transitQueries.getTransitCount().executeAsOne()
    }

    internal fun getTransitItems(): List<TransitItems> {
        return transitQueries.getTransitItems().executeAsList()
    }

    private fun insertCoordinates(
        coordinates: List<Coordinates>, roId: Long
    ) {
        coordinates.forEach { coords ->
            val lati1 = coords.latitude
            val long1 = coords.longitude
            coordinateQueries.insertCoordinates(
                // dbId = null,
                itId = roId,
                lati = lati1,
                long = long1
            )
        }
    }

    internal fun insertFaveRoute(roId: Long, time: String): Long {
        var result = 0L

        routeQueries.transaction {
            /* timeStamp must come before routeId
               to match SqlDelight signature */
            routeQueries.setFaveRoute(time, roId)
            result = routeQueries.selectChanges().executeAsOne()
        }

        return result
    }

    internal fun loadCommunityItems(items: CommunityItemCollection) {
        // Logger.d("HEY") { items.collection.size.toString() }
        items.collection.forEach { item ->
            communityQueries.insertCommunityItem(
                ciId = item.ciId.toLong(),
                name = item.name,
                text = item.text
            )
        }
    }

    internal fun loadRoutes(collection: FeatureCollectionRoutes3) {
        /* In the JSON file, several tracks have multiple (duplicate)
           items (one even has five), so start a UNIQUE list to "keep
           track" of which tracks we've added so far; however omit
           the "Connector" tracks from this list, which (obviously)
           all have the same name ... refer to EOF for list.add() */

        val walkingTracks = mutableSetOf<String>()

        collection.features.forEach { features ->

            if (features.geometry.isNotEmpty()) {

                /* Two items (Newton's Waterfall and Woodills
                   North tracks) have "Walking track" as their
                   walk classification (note lowercase "track") */
                if (features.properties.walkClass?.lowercase() == "walking track") {

                    /* If we get this far, then it's safe
                       to say that properties is NOT null */
                    features.properties.let { properties ->

                        /* If a track has NOT YET been added to the DB */
                        if (!walkingTracks.contains(properties.name)) {

                            properties.name?.let { propertiesName ->

                                /* There is one route duplicated with an incorrect
                                   name AND location. Woodhills : s/be Woodills */
                                if (!propertiesName.lowercase().contains(
                                    ROUTE_NAME_ANOMALY
                                )) {
                                    val lati0 = features.geometry[0].latitude
                                    val long0 = features.geometry[0].longitude
                                    var roId = 0L
                                    routeQueries.transaction {

                                        routeQueries.insertRoute(
                                            roId = null,
                                            ccId = features.id,
                                            lati = lati0,
                                            long = long0,
                                            area = properties.area?.trim(),
                                            plac = properties.place?.trim(),
                                            name = propertiesName.trim(),
                                            shar = properties.shared?.trim(),
                                            stat = properties.status?.trim(),
                                            city = 0.0, /* Distance to Chch city centre */
                                            desc = properties.description
                                                ?.trim()
                                                ?.replace(
                                                    oldValue = "\n", newValue = " "
                                                )?.replace( /* Note space for these two */
                                                    oldValue = "\r", newValue = " "
                                                ),
                                            dogs = properties.restriction?.trim(),
                                            dist = properties.distance?.trim(),
                                            dura = properties.duration?.trim(),
                                            time = String()
                                        )
                                        roId = routeQueries.selectLastInsertedRowId()
                                                           .executeAsOne()
                                    }

                                    if (roId > 0L) {
                                        insertCoordinates(features.geometry, roId)

                                        if (propertiesName != CONNECTOR_STRING) {
                                            walkingTracks.add(propertiesName)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        Logger.i("TIMER STOP") { getLocalTime(false) }
    }

    internal fun loadTransitItems(items: TransitItemCollection) {
        items.collection.forEach { item ->
            transitQueries.insertTransitItem(
                trId = item.trId.toLong(),
                name = item.name,
                text = item.text,
                extn = item.extn == 1, // Boolean
                link = item.link // Nullable text
            )
        }
    }

    internal fun setDistanceToCity(distance: Double, roId: Long) {
        routeQueries.setDistToCity(distance, roId)
    }

    internal fun setSettingSortByDist(value: Boolean): Long {
        var result = -1L
        settingsBooleanQueries.transaction {
            settingsBooleanQueries.updateSettingsBoolean(
                settingKey = PREFS_SORT_BY_DIST,
                settingVal = value
            )
            result = settingsBooleanQueries.selectChanges().executeAsOne()
        }
        return result
    }

    internal fun setSettingStartDest(value: String) {
        settingsStringQueries.updateSettingsString(
            settingKey = PREFS_START_SCREEN,
            settingVal = value
        )
    }
}