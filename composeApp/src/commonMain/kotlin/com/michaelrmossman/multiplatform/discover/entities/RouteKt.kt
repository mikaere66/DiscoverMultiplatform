package com.michaelrmossman.multiplatform.discover.entities

import com.michaelrmossman.multiplatform.discover.database.Coordinates

data class RouteKt(
    val roId: Long,
    val ccId: Long?,
    val lati: Double,
    val long: Double,
    val area: String?,
    val plac: String?,
    val name: String?,
    val shar: String?,
    val stat: String?,
    val desc: String?,
    val dogs: String?,
    val dist: String?,
    val dura: String?,
    var time: String?, // Note var

    /* This is the only difference, compared to
       the SqlDelight generated "Routes" object */
    val coords: List<Coordinates>
    /* The list of lists for each route
       is to allow for MultiLineStrings
       (i.e. more than one polyline) */
)
