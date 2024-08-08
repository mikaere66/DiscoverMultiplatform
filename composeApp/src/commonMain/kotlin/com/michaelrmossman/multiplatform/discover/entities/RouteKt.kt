package com.michaelrmossman.multiplatform.discover.entities

import com.michaelrmossman.multiplatform.discover.database.Coordinates

data class RouteKt(
    val roId: Long,
    val ccId: Long?,
    val area: String?,
    val plac: String?,
    val name: String?,
    val shar: String?,
    val stat: String?,
    val desc: String?,
    val dogs: String?,
    val iUrl: String?,
    val time: String?,
    /* This is the only difference, compared to
       the SqlDelight generated "Routes" object */
    val coords: List<Coordinates>
)
