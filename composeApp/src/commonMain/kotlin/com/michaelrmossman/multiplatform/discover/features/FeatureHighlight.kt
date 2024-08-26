package com.michaelrmossman.multiplatform.discover.features

import com.michaelrmossman.multiplatform.discover.features.geometry.GeometrySingle
import com.michaelrmossman.multiplatform.discover.features.properties.PropertiesHighlight
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureHighlight(

    @SerialName("type")
    val type: String,

    @SerialName("geometry")
    val geometry: GeometrySingle,

    @SerialName("properties")
    val properties: PropertiesHighlight
)