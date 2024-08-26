package com.michaelrmossman.multiplatform.discover.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DirectionsBike
import androidx.compose.material.icons.outlined.Recycling
import androidx.compose.material.icons.outlined.Interests
import androidx.compose.material.icons.outlined.Pets
import androidx.compose.material.icons.outlined.LocalDrink
import androidx.compose.material.icons.outlined.EvStation
import androidx.compose.material.icons.outlined.Wifi
import androidx.compose.material.icons.outlined.Forest
import androidx.compose.material.icons.outlined.History
import androidx.compose.material.icons.outlined.Wc
import androidx.compose.material.icons.outlined.NaturePeople
import androidx.compose.material.icons.outlined.ImagesearchRoller
import androidx.compose.material.icons.outlined.SpatialAudio
import androidx.compose.material.icons.outlined.Toys
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.BikeScooter
import com.michaelrmossman.multiplatform.discover.theme.summer
import com.michaelrmossman.multiplatform.discover.theme.lateSummer
import com.michaelrmossman.multiplatform.discover.theme.autumn
import com.michaelrmossman.multiplatform.discover.theme.winter
import com.michaelrmossman.multiplatform.discover.theme.lateWinter
import com.michaelrmossman.multiplatform.discover.theme.spring
import com.michaelrmossman.multiplatform.discover.theme.lateSpring

object Constants {

    val communityIcons = listOf(
        Icons.Outlined.SpatialAudio,
        Icons.Outlined.Recycling,
        Icons.Outlined.Interests,
        Icons.Outlined.Pets,
        Icons.Outlined.LocalDrink,
        Icons.Outlined.Wifi,
        Icons.Outlined.Forest,
        Icons.Outlined.History,
        Icons.Outlined.Wc,
        Icons.Outlined.NaturePeople,
        Icons.Outlined.ImagesearchRoller,
        Icons.Outlined.Toys
    )

    const val CLOSED_STRING = "Closed"
    const val CONNECTOR_STRING = "Connector"
    const val FAVE_DATE_FORMAT = "d MMM, y"
    const val FAVE_TIME_FORMAT_LONG = "H:mm:ss" // Debug
    const val FAVE_TIME_FORMAT_SHORT = "h:mm a" // Faves
    const val ITEM_TYPE_CYCLE = 1L
    const val ITEM_TYPE_ROUTE = 2L
    const val JSON_FILENAME_COMMUNITY =   "community_items.json"
    const val JSON_FILENAME_CYCLE_LANES = "cycle_lanes.geojson"
    const val JSON_FILENAME_ROUTES =      "walking_tracks.geojson"
    const val JSON_FILENAME_TRANSIT =     "transit_items.json"
    const val LAT_LNG_BOUNDS_PADDING = 140
    /* Colombo|Gloucester St (Tūranga) */
    const val LOCATION_CITY_CENTRE_LAT = -43.529851
    const val LOCATION_CITY_CENTRE_LNG = 172.636618
    /* Nga Puna Wai is 5.5km from city */
    const val ROUTE_CITY_CUTOFF = 5500.0
    /* Refer to comment in Database.kt */
    const val ROUTE_NAME_ANOMALY = "woodhills"
    const val SHARED_NO_STRING = "No"
    const val SHARED_YES_STRING = "Yes"

    const val PREFS_SORT_BY_DIST = "sort_by_dist"
    const val PREFS_START_SCREEN = "start_screen"

    /* Must be in the same order as
       Constants.seasonFilenames */
    val seasonColors = listOf(
        summer,
        lateSummer,
        autumn,
        winter,
        lateWinter,
        spring,
        lateSpring
    )

    /* In order that they appear on CCC website:
       https://www.ccc.govt.nz/parks-and-gardens/christchurch-botanic-gardens/attractions/seasonal-highlights/
    */
    val seasonFilenames = listOf(
        "1_summer.json",
        "2_late_summer.json",
        "3_autumn.json",
        "4_winter.json",
        "5_late_winter.json",
        "6_spring.json",
        "7_late_spring.json"
    )

    val transitIcons = listOf(
        Icons.Outlined.EvStation,
        Icons.Outlined.DirectionsBus,
        Icons.AutoMirrored.Outlined.DirectionsBike,
        Icons.Outlined.BikeScooter
    )
}