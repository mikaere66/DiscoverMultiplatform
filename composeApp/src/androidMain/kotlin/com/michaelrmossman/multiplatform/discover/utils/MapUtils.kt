package com.michaelrmossman.multiplatform.discover.utils

import com.google.android.gms.maps.model.LatLng

object MapUtils {

    fun getRouteCenterPoint(latLngList: List<LatLng>): LatLng {
        var lati = 0.0
        var long = 0.0
        latLngList.forEach { latLng ->
            lati = lati.plus(latLng.latitude)
            long = long.plus(latLng.longitude)
        }
        return LatLng(
            lati.div(latLngList.size),
            long.div(latLngList.size)
        )
    }
}