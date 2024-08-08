package com.michaelrmossman.multiplatform.discover.features

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureCollectionRoutes(

    @SerialName("type")
    val type: String,

    @SerialName("features")
    val features: List<Features>
)