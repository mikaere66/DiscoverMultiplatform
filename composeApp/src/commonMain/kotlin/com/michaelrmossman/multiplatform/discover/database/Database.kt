package com.michaelrmossman.multiplatform.discover.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.entities.points.highlights.Highlight
import com.michaelrmossman.multiplatform.discover.entities.points.highlights.FeatureCollectionHighlights
import com.michaelrmossman.multiplatform.discover.features.FeatureCollectionRoutes
import com.michaelrmossman.multiplatform.discover.utils.Constants.CONNECTOR_STRING
import com.michaelrmossman.multiplatform.discover.utils.DatabaseUtils.mapFeaturesToHighlights
import com.michaelrmossman.multiplatform.discover.utils.DatabaseUtils.mapRouteToRouteKt
import com.michaelrmossman.multiplatform.discover.utils.getLocalTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

internal class Database(
    databaseDriverFactory: DatabaseDriverFactory
) {

    private val database = DiscoverDatabase(
        databaseDriverFactory.createDriver()
    )
    private val coordinateQueries = database.coordinatesQueries
//    private val featureQueries = database.featuresQueries
//    private val monthQueries = database.monthsQueries
    private val routeQueries = database.routesQueries
//    private val seasonQueries = database.seasonsQueries
//    private val settingQueries = database.settingsQueries

    internal fun getCoordsCount(): Long {
        return coordinateQueries.getCoordsCount().executeAsOne()
    }

    internal fun getRouteById(roId: Long): Routes {
        return routeQueries.getRouteById(roId).executeAsOne()
    }

    internal fun getRouteKtById(roId: Long): RouteKt {
        val coordinates = coordinateQueries.getCoordsById(roId).executeAsList()
        val route = routeQueries.getRouteById(roId).executeAsOne()
        return mapRouteToRouteKt(coordinates, route)
    }

    internal fun getRouteCount(): Long { /* WHERE name != Connector */
        return routeQueries.getRouteCount(CONNECTOR_STRING).executeAsOne()
    }

    internal fun loadRoutes(collection: FeatureCollectionRoutes) {
        /* In the JSON file, there is about FIVE of everything, so start a UNIQUE
           list to keep track of which tracks we've added so far; however omit the
           "Connector" tracks from this list, as they all have the same name =) */
        val walkingTracks = mutableSetOf<String>()
        // val trackIds = mutableSetOf<Long>()

        collection.features.forEach { features ->

            // val trackId = features.properties.oid
            if (!walkingTracks.contains(features.properties.name)) {
            // if (!trackIds.contains(trackId)) {

                if (features.properties.feature == "walkingtracks") {

                    /* If we get this far, then it has been
                       accepted that properties is NOT null */
                    features.properties.data?.properties?.let { properties ->

                        val lati0 = features.geometry[0].latitude
                        val long0 = features.geometry[0].longitude
                        var roId = 0L
                        routeQueries.transaction {

                            routeQueries.insertRoute(
                                roId = null,
                                ccId = properties.id,
                                lati = lati0,
                                long = long0,
                                area = properties.area?.trim(),
                                plac = properties.place?.trim(),
                                name = properties.name?.trim(),
                                shar = properties.shared?.trim(),
                                stat = properties.status?.trim(),
                                desc = properties.description?.trim()
                                    ?.replace(
                                        oldValue = "\n", newValue = " "
                                    )?.replace( /* Note space for these two */
                                        oldValue = "\r", newValue = " "
                                    ),
                                dogs = properties.restriction?.trim(),
                                iUrl = properties.photoUrl?.trim(),
                                time = String()
                            )
                            roId = routeQueries.selectLastInsertedRowId()
                                .executeAsOne()
                        }

                        if (roId > 0L) {
                            features.geometry.forEach { coords ->
                                val lati1 = coords.latitude
                                val long1 = coords.longitude
                                coordinateQueries.insertCoordinates(
                                    dbId = null,
                                    itId = roId,
                                    lati = lati1,
                                    long = long1
                                )
                            }

                            /* TODO: Mt Pleasant connector (251) */
                            properties.name?.let { name ->
                                if (name != CONNECTOR_STRING) {
                                    walkingTracks.add(name)
                                }
                            }
                        }
                    }
                }
            }
        }
        Logger.i("TIMER STOP") { getLocalTime() }
    }
}