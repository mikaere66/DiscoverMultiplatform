package com.michaelrmossman.multiplatform.discover.features

import com.michaelrmossman.multiplatform.discover.entities.Coordinates
import com.michaelrmossman.multiplatform.discover.features.geometry.GeometrySerializer
import com.michaelrmossman.multiplatform.discover.features.properties.PropertiesRoute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturesRoute(

    @SerialName("id")
    val id: Long,

    @Serializable(with = GeometrySerializer::class)
    val geometry: List<Coordinates>,

    @SerialName("properties")
    val properties: PropertiesRoute,

    @SerialName("type")
    val type: String
)