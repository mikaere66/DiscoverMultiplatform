package com.michaelrmossman.multiplatform.discover.features.geometry

import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.entities.Coordinates
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonDecoder
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray

object GeometrySerializer3: KSerializer<List<Coordinates>> {

    override val descriptor: SerialDescriptor
        get() = PrimitiveSerialDescriptor("type", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): List<Coordinates> {
        val json = ((decoder as JsonDecoder).decodeJsonElement() as JsonObject)
        return parseGeometry(json)
    }

    private fun parseGeometry(json: JsonObject): List<Coordinates> {
        val coordsList = mutableListOf<Coordinates>()

        val type        = json["type"]        ?: return emptyList()
        val coordinates = json["coordinates"] ?: return emptyList()

        try {

            val elementType: String = Json.decodeFromJsonElement(type)
            val coordsArray = coordinates.jsonArray

            if (coordsArray.isNotEmpty()) {

                /* All other elementTypes are ignored */
                if (elementType == "LineString") {

                    val subList = getSubList(coordsArray)
                    coordsList.addAll(subList)
                }

                // Logger.i("HEY") { "$elementType ${ coordsList.size }" }
            }

        } catch (exception: Exception) {
            Logger.e(exception) { "Shite!" }
        }

        return coordsList
    }

    private fun getSubList(array: JsonArray): List<Coordinates> {
        val coordsList = mutableListOf<Coordinates>()

        if (array.isNotEmpty()) {
            array.forEach { element ->
                val coords: List<Double> =
                    Json.decodeFromJsonElement(element)

                /* Be aware that these two are reversed (by
                   Google Maps standards) i.e. in the .json
                   files, they appear as longitude,latitude */
                coordsList.add(
                    Coordinates(
                        latitude  = coords[1],
                        longitude = coords[0]
                    )
                )
            }
        }

        return coordsList
    }

    override fun serialize(encoder: Encoder, value: List<Coordinates>) {
        TODO("Not yet implemented")
    }
}