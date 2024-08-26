package com.michaelrmossman.multiplatform.discover.features

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeaturesTransitItem(

    @SerialName("id")
    val trId: Int,

    @SerialName("name")
    val name: String,

    @SerialName("text")
    val text: String,

    @SerialName("extn")
    val extn: Int,

    @SerialName("link")
    val link: String?
)