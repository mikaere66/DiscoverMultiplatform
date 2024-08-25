package com.michaelrmossman.multiplatform.discover.database

import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.utils.JsonUtils
import com.michaelrmossman.multiplatform.discover.utils.getDistanceTo
import discovermultiplatform.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

class DatabaseImpl(
    databaseDriverFactory: DatabaseDriverFactory,
    private val json: JsonUtils
) {
    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class)
    fun deleteFaveRoute(roId: Long): Long {
        return database.deleteFaveRoute(roId)
    }

    @Throws(Exception::class)
    fun getCommunityCount(): Long {
        return database.getCommunityCount()
    }

    @Throws(Exception::class)
    fun getCommunityItems(): List<CommunityItems> {
        return database.getCommunityItems()
    }

    @Throws(Exception::class)
    fun getCoordsCount(): Long {
        return database.getCoordsCount()
    }

    @Throws(Exception::class)
    fun getRandomRouteId(): Long {
        return database.getRandomRouteId()
    }

    @Throws(Exception::class)
    fun getRouteById(roId: Long): Routes {
        return database.getRouteById(roId)
    }

    @Throws(Exception::class)
    fun getRouteCount(): Long {
        return database.getRouteCount()
    }

    @Throws(Exception::class)
    fun getRouteDistToCityCount(): Long {
        return database.getRouteDistToCityCount()
    }

    @Throws(Exception::class)
    fun getRouteKtById(roId: Long): RouteKt {
        return database.getRouteKtById(roId)
    }

    @Throws(Exception::class)
    fun getRoutes(
        byDistance: Boolean, connectors: Boolean
    ) : List<Routes> {
        return database.getRoutes(byDistance, connectors)
    }

    @Throws(Exception::class)
    fun getRoutesKt(connectors: Boolean): List<RouteKt> {
        return database.getRoutesKt(connectors)
    }

    @Throws(Exception::class)
    fun getSettingsBooleanCount(): Long {
        return database.getSettingsBooleanCount()
    }

    @Throws(Exception::class)
    fun getSettingSortByDist(): SettingsBoolean {
        return database.getSettingSortByDist()
    }

    @Throws(Exception::class)
    fun getSettingsStringCount(): Long {
        return database.getSettingsStringCount()
    }

    @Throws(Exception::class)
    fun getSettingStartDest(): SettingsString {
        return database.getSettingStartDest()
    }

    @Throws(Exception::class)
    fun getTransitCount(): Long {
        return database.getTransitCount()
    }

    @Throws(Exception::class)
    fun getTransitItems(): List<TransitItems> {
        return database.getTransitItems()
    }

    @Throws(Exception::class)
    fun insertFaveRoute(roId: Long, time: String): Long {
        return database.insertFaveRoute(roId, time)
    }

    @Throws(Exception::class)
    suspend fun loadCommunityItems() {
        val fullPath = "files".plus(
            "/"
        ).plus("community_items.json")
        /* Annotation reqd for readBytes.
           Read each file as a ByteArray */
        @OptIn(ExperimentalResourceApi::class)
        val bytes = Res.readBytes(fullPath)
        /* ... then decode the bytes to JSON */
        val jsonString = bytes.decodeToString()
        json.parseCommunityFile(jsonString).also { items ->
            database.loadCommunityItems(items)
        }
    }

    @Throws(Exception::class)
    suspend fun loadRouteDistances(routes: List<Routes>) {
        routes.forEach { route ->
            val distance = getDistanceTo(route.lati, route.long)
            // Logger.d("HEY") { "|${ route.name }|$distance" }
            database.setDistanceToCity(distance, route.roId)
        }
    }

    @Throws(Exception::class)
    suspend fun loadRoutes() {
        val fullPath = "files".plus(
            "/"
        ).plus("walking_tracks_2.geojson")
        /* Annotation reqd for readBytes.
           Read each file as a ByteArray */
        @OptIn(ExperimentalResourceApi::class)
        val bytes = Res.readBytes(fullPath)
        /* ... then decode the bytes to JSON */
        val jsonString = bytes.decodeToString()
        json.parseRoutesFile(jsonString).also { collection ->
            database.loadRoutes(collection)
        }
    }

    @Throws(Exception::class)
    suspend fun loadTransitItems() {
        val fullPath = "files".plus(
            "/"
        ).plus("transit_items.json")
        /* Annotation reqd for readBytes.
           Read each file as a ByteArray */
        @OptIn(ExperimentalResourceApi::class)
        val bytes = Res.readBytes(fullPath)
        /* ... then decode the bytes to JSON */
        val jsonString = bytes.decodeToString()
        json.parseTransitFile(jsonString).also { items ->
            database.loadTransitItems(items)
        }
    }

    @Throws(Exception::class)
    fun setDistanceToCity(distance: Double, roId: Long) {
        database.setDistanceToCity(distance, roId)
    }

    @Throws(Exception::class)
    fun setSettingSortByDist(value: Boolean): Long {
        return database.setSettingSortByDist(value)
    }

    @Throws(Exception::class)
    fun setSettingStartDest(value: String) {
        database.setSettingStartDest(value)
    }
}