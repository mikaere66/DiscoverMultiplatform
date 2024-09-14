package com.michaelrmossman.multiplatform.discover.collections

import com.michaelrmossman.multiplatform.discover.collections.features.FeaturesTransitItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureCollectionTransitItems(

    @SerialName("features")
    val collection: List<FeaturesTransitItem>
)