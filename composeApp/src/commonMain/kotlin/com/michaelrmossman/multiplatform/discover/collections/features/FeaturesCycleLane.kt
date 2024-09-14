package com.michaelrmossman.multiplatform.discover.collections.features

import com.michaelrmossman.multiplatform.discover.entities.CoordsKt
import com.michaelrmossman.multiplatform.discover.collections.geometry.GeometrySerializer
import com.michaelrmossman.multiplatform.discover.collections.properties.PropertiesCycleLane
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturesCycleLane(

    @Serializable(with = GeometrySerializer::class)
    val geometry: List<CoordsKt>,

    @SerialName("properties")
    val properties: PropertiesCycleLane, // 4947

    @SerialName("type")
    val type: String
)