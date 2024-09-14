package com.michaelrmossman.multiplatform.discover.entities

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import com.michaelrmossman.multiplatform.discover.database.Routes
import com.michaelrmossman.multiplatform.discover.utils.getTextWithPlaceholder
import discovermultiplatform.composeapp.generated.resources.Res
import discovermultiplatform.composeapp.generated.resources.route_area
import discovermultiplatform.composeapp.generated.resources.route_desc
import discovermultiplatform.composeapp.generated.resources.route_dist
import discovermultiplatform.composeapp.generated.resources.route_dogs
import discovermultiplatform.composeapp.generated.resources.route_dura
import discovermultiplatform.composeapp.generated.resources.route_no_data
import discovermultiplatform.composeapp.generated.resources.route_place
import discovermultiplatform.composeapp.generated.resources.route_shared
import discovermultiplatform.composeapp.generated.resources.route_status
import org.jetbrains.compose.resources.stringResource

data class RouteKt(
    val roId: Long,
    val ccId: Long?,
    val lati: Double,
    val long: Double,
    val area: AnnotatedString,
    val plac: AnnotatedString,
    val name: String,
    val shar: AnnotatedString,
    val stat: AnnotatedString,
    val desc: AnnotatedString,
    val dogs: AnnotatedString,
    val dist: AnnotatedString,
    val dura: AnnotatedString,
    var time: String?, // Note var
    /* This is the only difference, compared to
       the SqlDelight generated "Routes" object */
    val coords: List<CoordsKt> = emptyList()
    /* The list of lists for each route
       is to allow for MultiLineStrings
       (i.e. more than one polyline) */
)

@Composable
fun Routes.toRouteKt(
    coords: List<CoordsKt> = emptyList()
) : RouteKt {
    return RouteKt(
        roId = this.roId,
        ccId = this.ccId,
        lati = this.lati,
        long = this.long,
        area = getTextWithPlaceholder(
            resource = Res.string.route_area,
            string = this.area
        ),
        plac = getTextWithPlaceholder(
            resource = Res.string.route_place,
            string = this.plac
        ),
        name = this.name ?: stringResource(
            resource = Res.string.route_no_data
        ),
        shar = getTextWithPlaceholder(
            resource = Res.string.route_shared,
            string = this.shar
        ),
        stat = getTextWithPlaceholder(
            resource = Res.string.route_status,
            string = this.stat
        ),
        desc = getTextWithPlaceholder(
            resource = Res.string.route_desc,
            string = this.desc
        ),
        dogs = getTextWithPlaceholder(
            resource = Res.string.route_dogs,
            string = this.dogs
        ),
        dist = getTextWithPlaceholder(
            resource = Res.string.route_dist,
            string = this.dist
        ),
        dura = getTextWithPlaceholder(
            resource = Res.string.route_dura,
            string = this.dura
        ),
        time = this.time,
        coords = coords
    )
}

//suspend fun Routes.toRouteKt(
//    coords: List<CoordsKt> = emptyList()
//) : RouteKt {
//    return RouteKt(
//        roId = this.roId,
//        ccId = this.ccId,
//        lati = this.lati,
//        long = this.long,
//        area = getTextWithPlaceholder(
//            resource = Res.string.route_area,
//            string = this.area
//        ),
//        plac = getTextWithPlaceholder(
//            resource = Res.string.route_place,
//            string = this.plac
//        ),
//        name = this.name ?: getString(
//            resource = Res.string.route_no_data
//        ),
//        shar = getTextWithPlaceholder(
//            resource = Res.string.route_shared,
//            string = this.shar
//        ),
//        stat = getTextWithPlaceholder(
//            resource = Res.string.route_status,
//            string = this.stat
//        ),
//        desc = getTextWithPlaceholder(
//            resource = Res.string.route_desc,
//            string = this.desc
//        ),
//        dogs = getTextWithPlaceholder(
//            resource = Res.string.route_dogs,
//            string = this.dogs
//        ),
//        dist = getTextWithPlaceholder(
//            resource = Res.string.route_dist,
//            string = this.dist
//        ),
//        dura = getTextWithPlaceholder(
//            resource = Res.string.route_dura,
//            string = this.dura
//        ),
//        time = this.time,
//        coords = coords
//    )
//}