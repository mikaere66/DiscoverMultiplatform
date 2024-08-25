package com.michaelrmossman.multiplatform.discover.utils

import com.michaelrmossman.multiplatform.discover.features.CommunityItemCollection
import com.michaelrmossman.multiplatform.discover.features.FeatureCollectionRoutes3
import com.michaelrmossman.multiplatform.discover.features.TransitItemCollection
import com.michaelrmossman.multiplatform.discover.entities.points.highlights.FeatureCollectionHighlights
import kotlinx.serialization.json.Json

class JsonUtils {

    private val json = Json {
        coerceInputValues = true // Default is false
        ignoreUnknownKeys = true // Default is false
    }

    fun parseCommunityFile(jsonString: String): CommunityItemCollection {

        return json.decodeFromString<CommunityItemCollection>(jsonString)
    }

    fun parseHighlightsFile(jsonString: String): FeatureCollectionHighlights {

        return json.decodeFromString<FeatureCollectionHighlights>(jsonString)
    }

    fun parseRoutesFile(jsonString: String): FeatureCollectionRoutes3 {

        return json.decodeFromString<FeatureCollectionRoutes3>(jsonString)
    }

    fun parseTransitFile(jsonString: String): TransitItemCollection {

        return json.decodeFromString<TransitItemCollection>(jsonString)
    }
}