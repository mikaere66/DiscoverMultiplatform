package com.michaelrmossman.multiplatform.discover.collections.features

import com.michaelrmossman.multiplatform.discover.entities.CoordsKt
import com.michaelrmossman.multiplatform.discover.collections.geometry.GeometrySerializer
import com.michaelrmossman.multiplatform.discover.collections.properties.PropertiesRoute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturesRoute(

    @SerialName("id")
    val id: Long,

    @Serializable(with = GeometrySerializer::class)
    val geometry: List<CoordsKt>,

    @SerialName("properties")
    val properties: PropertiesRoute,

    @SerialName("type")
    val type: String
)