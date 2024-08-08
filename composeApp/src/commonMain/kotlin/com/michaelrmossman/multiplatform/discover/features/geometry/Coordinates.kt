package com.michaelrmossman.multiplatform.discover.features.geometry

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(

    val latitude : Double,
    val longitude: Double
)