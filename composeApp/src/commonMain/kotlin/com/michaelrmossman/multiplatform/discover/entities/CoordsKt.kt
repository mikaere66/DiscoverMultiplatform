package com.michaelrmossman.multiplatform.discover.entities

import com.michaelrmossman.multiplatform.discover.database.Coordinates
import kotlinx.serialization.Serializable

@Serializable
data class CoordsKt(

    val latitude : Double,
    val longitude: Double
)

fun Coordinates.toCoordsKt(): CoordsKt {
    return CoordsKt(
        latitude = this.lati,
        longitude = this.long
    )
}