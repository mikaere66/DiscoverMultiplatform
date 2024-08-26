package com.michaelrmossman.multiplatform.discover.features

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturesCommunityItem(

    @SerialName("ciId")
    val ciId: Int,

    @SerialName("name")
    val name: String,

    @SerialName("text")
    val text: String
)