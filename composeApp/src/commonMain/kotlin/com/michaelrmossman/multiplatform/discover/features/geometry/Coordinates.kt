package com.michaelrmossman.multiplatform.discover.features.geometry

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(

    /* Be aware that these two are reversed (by
       Google Maps standards) i.e. in the .json
       files, they appear as longitude,latitude */
    val longitude: Double,
    val latitude : Double
)