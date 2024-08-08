package com.michaelrmossman.multiplatform.discover.entities.points.highlights

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Geometry(

    @SerialName("type")
    val type: String,

    @SerialName("coordinates")
    val coordinates: List<Double>
)
