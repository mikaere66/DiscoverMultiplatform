package com.michaelrmossman.multiplatform.discover.collections.features

import com.michaelrmossman.multiplatform.discover.collections.geometry.GeometrySingle
import com.michaelrmossman.multiplatform.discover.collections.properties.PropertiesHighlight
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