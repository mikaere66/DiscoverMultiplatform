package com.michaelrmossman.multiplatform.discover.features

import com.michaelrmossman.multiplatform.discover.entities.Coordinates
import com.michaelrmossman.multiplatform.discover.features.geometry.GeometrySerializer
import com.michaelrmossman.multiplatform.discover.features.properties.PropertiesCycleLane
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturesCycleLane(

    @Serializable(with = GeometrySerializer::class)
    val geometry: List<Coordinates>,

    @SerialName("properties")
    val properties: PropertiesCycleLane, // 4947

    @SerialName("type")
    val type: String
)