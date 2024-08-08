package com.michaelrmossman.multiplatform.discover.features.properties.data.properties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties(

//    @SerialName("RouteArea")
//    val area: String? = null,

    @SerialName("RouteArea")
    val area: String? = null,

//    @SerialName("data")
//    val data: Data? = null,

    @SerialName("Description")
    val description: String? = null,

    /* Only used to determine if this
       has property : "walkingtracks" */
    @SerialName("feature")
    val feature: String? = null,

    @SerialName("RouteID")
    val id: Long? = null,

//    @SerialName("name")
//    val name: String? = null,

    @SerialName("RouteName")
    val name: String,

    @SerialName("RouteServiceStatus")
    val status: String? = null,

    @SerialName("Comment")
    val comment: String? = null,

    /* RouteDistance in JSON file jumps
       between integers and strings! */
    @SerialName("drvRouteDistance")
    val distance: String? = null,

    /* RouteTravelTime in JSON file jumps
       between integers and strings! */
    @SerialName("drvRouteTravelTime")
    val duration: String? = null,

    @SerialName("PhotoURL")
    val photo: String? = null,

//    @SerialName("RoutePlace")
//    val place: String? = null,

    @SerialName("RoutePlace")
    val place: String? = null,

    @SerialName("RouteDogRestriction")
    val restriction: String? = null,

    @SerialName("RouteSharedUse")
    val shared: String? = null,

    @SerialName("RouteTraffic")
    val traffic: String? = null
) {
    val photoUrl: String? = when (photo?.isNotBlank()) {
        false -> null
        else -> photo
    }
}
