package com.michaelrmossman.multiplatform.discover.database

import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.entities.points.highlights.Highlight
import com.michaelrmossman.multiplatform.discover.utils.Constants.assetFilenames
import com.michaelrmossman.multiplatform.discover.utils.DatabaseUtils.getSeasonMonths
import com.michaelrmossman.multiplatform.discover.utils.JsonUtils
import discovermultiplatform.composeapp.generated.resources.Res
import kotlinx.coroutines.flow.Flow
import org.jetbrains.compose.resources.ExperimentalResourceApi

class DatabaseImpl(
    databaseDriverFactory: DatabaseDriverFactory,
    private val json: JsonUtils
) {
    private val database = Database(databaseDriverFactory)

    @Throws(Exception::class)
    fun getCoordsCount(): Long {
        return database.getCoordsCount()
    }

    @Throws(Exception::class)
    fun getRouteById(roId: Long): Routes {
        return database.getRouteById(roId)
    }

    @Throws(Exception::class)
    fun getRouteKtById(roId: Long): RouteKt {
        return database.getRouteKtById(roId)
    }

    @Throws(Exception::class)
    fun getRouteCount(): Long {
        return database.getRouteCount()
    }

    @Throws(Exception::class)
    suspend fun loadRoutes() {
        val fullPath = "files".plus(
            "/"
        ).plus("walking_tracks.geojson")
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
}