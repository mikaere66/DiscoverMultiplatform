package com.michaelrmossman.multiplatform.discover.collections.properties

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PropertiesHighlight(

    @SerialName("SeasonalName")
    val seasonalName: String,

    @SerialName("SeasonalCode")
    val seasonalCode: String,

    @SerialName("Status")
    val status: String,

    @SerialName("Description")
    val description: String,

    @SerialName("Photo")
    val photo: String
) {
    val photoUrl: String? = when (photo.isNotBlank()) {
        false -> null
        else -> photo
    }
}