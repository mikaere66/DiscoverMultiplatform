package com.michaelrmossman.multiplatform.discover.features.geometry

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GeometrySingle(

    @SerialName("type")
    val type: String,

    @SerialName("coordinates")
    val coordinates: List<Double>
)