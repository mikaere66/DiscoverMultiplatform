package com.michaelrmossman.multiplatform.discover.entities

import kotlinx.serialization.Serializable

@Serializable
data class Coordinates(

    val latitude : Double,
    val longitude: Double
)