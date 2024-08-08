package com.michaelrmossman.multiplatform.discover.entities.points.highlights

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Feature(

    @SerialName("type")
    val type: String,

    @SerialName("geometry")
    val geometry: Geometry,

    @SerialName("properties")
    val properties: Properties
)
