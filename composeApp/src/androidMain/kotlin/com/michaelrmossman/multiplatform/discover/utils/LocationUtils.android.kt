package com.michaelrmossman.multiplatform.discover.utils

import android.location.Location
import co.touchlab.kermit.Logger
import com.michaelrmossman.multiplatform.discover.utils.Constants.LOCATION_CITY_CENTRE_LAT
import com.michaelrmossman.multiplatform.discover.utils.Constants.LOCATION_CITY_CENTRE_LNG

// https://developer.android.com/reference/android/location/Location.html#distanceTo(android.location.Location)
actual fun getDistanceTo(lati: Double, long: Double): Double {
    val cityCentre = Location(String())
    cityCentre.latitude = LOCATION_CITY_CENTRE_LAT
    cityCentre.longitude = LOCATION_CITY_CENTRE_LNG

    val targetLocation = Location(String())
    targetLocation.latitude = lati
    targetLocation.longitude = long

    val distance = targetLocation.distanceTo(cityCentre) // Float
    // Logger.d("HEY") { distance.toString() }
    return distance.toDouble()
}