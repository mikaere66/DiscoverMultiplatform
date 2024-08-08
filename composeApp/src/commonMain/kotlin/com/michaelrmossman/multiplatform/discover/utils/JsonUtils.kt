package com.michaelrmossman.multiplatform.discover.utils

import com.michaelrmossman.multiplatform.discover.features.FeatureCollectionRoutes
import com.michaelrmossman.multiplatform.discover.entities.points.highlights.FeatureCollectionHighlights
import kotlinx.serialization.json.Json

class JsonUtils {

    private val json = Json {
        ignoreUnknownKeys = true  // Default is false
        useAlternativeNames = true // Default is true
    }

    fun parseHighlightsFile(jsonString: String): FeatureCollectionHighlights {

        return json.decodeFromString<FeatureCollectionHighlights>(jsonString)
    }

    fun parseRoutesFile(jsonString: String): FeatureCollectionRoutes {

        return json.decodeFromString<FeatureCollectionRoutes>(jsonString)
    }
}