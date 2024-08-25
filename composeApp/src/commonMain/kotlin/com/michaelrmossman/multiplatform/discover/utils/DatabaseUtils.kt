package com.michaelrmossman.multiplatform.discover.utils

import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.database.Coordinates
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.entities.RouteKt
import com.michaelrmossman.multiplatform.discover.entities.points.highlights.Highlight

object DatabaseUtils {

    fun getSeasonMonths(): HashMap<Long, List<Long>> {
        /* Give each month a season Id, from Dec.
           to November (Summer to Late Spring) */
        return hashMapOf(
            1L to listOf(12L,1L),   // Summer
            2L to listOf(2L),       // Late "
            3L to listOf(3L,4L,5L), // Autumn
            4L to listOf(6L,7L),    // Winter
            5L to listOf(8L),       // Late "
            6L to listOf(9L,10L),   // Spring
            7L to listOf(11L)       // Late "
        )
    }

    fun mapRouteToRouteKt(
        coordinates: List<Coordinates>, route: Routes
    ) : RouteKt {
        return RouteKt(
            route.roId,
            route.ccId,
            route.lati,
            route.long,
            route.area,
            route.plac,
            route.name,
            route.shar,
            route.stat,
            route.desc,
            route.dogs,
            route.dist,
            route.dura,
            route.time,
            // routeKtCoordsList
            coordinates
        )
    }

    fun mapFeaturesToHighlights(
        feId: Long,
        name: String,
        coId: Long,
        stat: String,
        desc: String,
        iUrl: String?,
        lati: Double,
        long: Double,
        // posi: String,
        time: String?
    ) : Highlight {
        return Highlight(
            shId = feId.toInt(),
            name = name,
            coId = coId.toInt(),
            stat = stat,
            desc = desc,
            iUrl = iUrl,
            lati = lati,
            long = long,
            // posi = posi,
            time = time
        )
    }
}