package com.michaelrmossman.multiplatform.discover.features.properties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties3(

    @SerialName("routearea")
    val area: String? = null,

    @SerialName("description")
    val description: String? = null,

    /* Only used to determine if this
       has property : "Walking Track" */
    @SerialName("walkclassification")
    val walkClass: String? = null,

//    @SerialName("RouteID")
//    val id: Long? = null,

    /* Although this seems crazy, the ARE two
       items that have null values as name */
    @SerialName("routename")
    val name: String? = null,

    @SerialName("routeservicestatus")
    val status: String? = null,

    @SerialName("Comment")
    val comment: String? = null,

    /* RouteDistance in JSON file jumps
       between integers and strings! */
    @SerialName("drvroutedistance")
    val distance: String? = null,

    /* RouteTravelTime in JSON file jumps
       between integers and strings! */
    @SerialName("drvroutetraveltime")
    val duration: String? = null,

    @SerialName("photourl")
    val photo: String? = null,

    @SerialName("routeplace")
    val place: String? = null,

    @SerialName("routedogrestriction")
    val restriction: String? = null,

    @SerialName("routeshareduse")
    val shared: String? = null,

//    @SerialName("RouteTraffic")
//    val traffic: String? = null
) {
    val photoUrl: String? = when (photo?.isNotBlank()) {
        false -> null
        else -> photo
    }
}