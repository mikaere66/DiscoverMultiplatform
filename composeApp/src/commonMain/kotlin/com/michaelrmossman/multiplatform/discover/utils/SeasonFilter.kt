package com.michaelrmossman.multiplatform.discover.utils

import com.michaelrmossman.multiplatform.discover.entities.points.highlights.Highlight

class SeasonFilter {

    fun filterBySeason(
        highlights: List<Highlight>, season: Int
    ) : List<Highlight> {
        if (highlights.isEmpty()) {
            return highlights
        }

        if (season == 0) { // All seasons
            return highlights
        }

        return highlights.filter { highlight ->
            highlight.coId == season
        }
    }
}
