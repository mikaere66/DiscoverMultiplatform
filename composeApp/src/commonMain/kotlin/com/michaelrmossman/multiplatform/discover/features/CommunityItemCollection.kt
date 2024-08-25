package com.michaelrmossman.multiplatform.discover.features

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommunityItemCollection(

    @SerialName("features")
    val collection: List<CommunityItems>
)