package com.michaelrmossman.multiplatform.discover.collections

import com.michaelrmossman.multiplatform.discover.collections.features.FeatureHighlight
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureCollectionHighlights(

    @SerialName("type")
    val type: String,

    @SerialName("features")
    val features: List<FeatureHighlight>
)