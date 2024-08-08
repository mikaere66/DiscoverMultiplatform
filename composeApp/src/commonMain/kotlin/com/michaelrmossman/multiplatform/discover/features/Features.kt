package com.michaelrmossman.multiplatform.discover.features

import com.michaelrmossman.multiplatform.discover.features.geometry.Coordinates
import com.michaelrmossman.multiplatform.discover.features.geometry.GeometrySerializer
import com.michaelrmossman.multiplatform.discover.features.properties.Properties
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Features(

    @SerialName("type")
    val type: String,

    @Serializable(with = GeometrySerializer::class)
    val geometry: List<Coordinates>,

    @SerialName("properties")
    val properties: Properties
)