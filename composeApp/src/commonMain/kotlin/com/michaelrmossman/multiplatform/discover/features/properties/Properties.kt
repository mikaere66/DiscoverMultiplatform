package com.michaelrmossman.multiplatform.discover.features.properties

import com.michaelrmossman.multiplatform.discover.features.properties.data.Data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Properties(

    @SerialName("feature")
    val feature: String,

    @SerialName("name")
    val name: String,

    @SerialName("data")
    val data: Data? = null,

//    @SerialName("desc")
//    val description: String? = null,

    @SerialName("oid")
    val oid: Long? = null,

//    @SerialName("status")
//    val status: String? = null,

//    @SerialName("traffic")
//    val traffic: String? = null,

//    @SerialName("Comment")
//    val comment: String? = null,

//    @SerialName("RouteArea")
//    val area: String? = null,

//    @SerialName("RoutePlace")
//    val place: String? = null,

//    @SerialName("PhotoURL")
//    val photo: String? = null
)
//{
//    val photoUrl: String? = when (photo?.isNotBlank()) {
//        false -> null
//        else -> photo
//    }
//}
