package com.michaelrmossman.multiplatform.discover.features.properties.data

import com.michaelrmossman.multiplatform.discover.features.properties.data.properties.Properties
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(

    @SerialName("type")
    val type: String,

//    @Serializable(with = GeometrySerializer::class)
//    val geometry: List<Coordinates>,

    @SerialName("properties")
    val properties: Properties
)