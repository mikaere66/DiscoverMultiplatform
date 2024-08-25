package com.michaelrmossman.multiplatform.discover.features

import com.michaelrmossman.multiplatform.discover.entities.Coordinates
import com.michaelrmossman.multiplatform.discover.features.geometry.GeometrySerializer3
import com.michaelrmossman.multiplatform.discover.features.properties.Properties3
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Features3(

    @SerialName("id")
    val id: Long,

    @Serializable(with = GeometrySerializer3::class)
    val geometry: List<Coordinates>,

    @SerialName("properties")
    val properties: Properties3,

    @SerialName("type")
    val type: String
)