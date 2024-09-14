package com.michaelrmossman.multiplatform.discover.utils

import com.michaelrmossman.multiplatform.discover.collections.FeatureCollectionCommunityItems
import com.michaelrmossman.multiplatform.discover.collections.FeatureCollectionCycleLanes
import com.michaelrmossman.multiplatform.discover.collections.FeatureCollectionRoutes
import com.michaelrmossman.multiplatform.discover.collections.FeatureCollectionTransitItems
import com.michaelrmossman.multiplatform.discover.collections.FeatureCollectionHighlights
import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import discovermultiplatform.composeapp.generated.resources.Res
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.ExperimentalResourceApi

class JsonUtils {

    @NativeCoroutines
    suspend fun getJsonString(filename: String): String {
        val fullPath = "files".plus(
            "/"
        ).plus(filename)
        /* Annotation reqd for readBytes.
           Read each file as a ByteArray */
        @OptIn(ExperimentalResourceApi::class)
        val bytes = Res.readBytes(fullPath)
        /* ... then decode the bytes to JSON */
        return bytes.decodeToString()
    }

    private val json = Json {
        coerceInputValues = true // Default is false
        ignoreUnknownKeys = true // Default is false
    }

    fun parseCommunityFile(jsonString: String): FeatureCollectionCommunityItems {

        return json.decodeFromString<FeatureCollectionCommunityItems>(jsonString)
    }

    fun parseCycleLanesFile(jsonString: String): FeatureCollectionCycleLanes {

        return json.decodeFromString<FeatureCollectionCycleLanes>(jsonString)
    }

    fun parseHighlightsFile(jsonString: String): FeatureCollectionHighlights {

        return json.decodeFromString<FeatureCollectionHighlights>(jsonString)
    }

    fun parseRoutesFile(jsonString: String): FeatureCollectionRoutes {

        return json.decodeFromString<FeatureCollectionRoutes>(jsonString)
    }

    fun parseTransitFile(jsonString: String): FeatureCollectionTransitItems {

        return json.decodeFromString<FeatureCollectionTransitItems>(jsonString)
    }
}
