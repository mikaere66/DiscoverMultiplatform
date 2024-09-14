package com.michaelrmossman.multiplatform.discover.collections

import com.michaelrmossman.multiplatform.discover.collections.features.FeaturesRoute
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureCollectionRoutes(

    @SerialName("type")
    val type: String,

    @SerialName("features")
    val features: List<FeaturesRoute>
)