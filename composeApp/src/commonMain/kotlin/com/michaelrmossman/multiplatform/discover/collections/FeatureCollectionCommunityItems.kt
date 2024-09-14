package com.michaelrmossman.multiplatform.discover.collections

import com.michaelrmossman.multiplatform.discover.collections.features.FeaturesCommunityItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeatureCollectionCommunityItems(

    @SerialName("features")
    val collection: List<FeaturesCommunityItem>
)