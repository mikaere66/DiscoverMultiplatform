package com.michaelrmossman.multiplatform.discover.utils

import com.michaelrmossman.multiplatform.discover.theme.summer
import com.michaelrmossman.multiplatform.discover.theme.lateSummer
import com.michaelrmossman.multiplatform.discover.theme.autumn
import com.michaelrmossman.multiplatform.discover.theme.winter
import com.michaelrmossman.multiplatform.discover.theme.lateWinter
import com.michaelrmossman.multiplatform.discover.theme.spring
import com.michaelrmossman.multiplatform.discover.theme.lateSpring

object Constants {

    /* In order that they appear on CCC website:
       https://www.ccc.govt.nz/parks-and-gardens/christchurch-botanic-gardens/attractions/seasonal-highlights/
    */
    val assetFilenames = listOf(
        "1_summer.json",
        "2_late_summer.json",
        "3_autumn.json",
        "4_winter.json",
        "5_late_winter.json",
        "6_spring.json",
        "7_late_spring.json"
    )

    const val CONNECTOR_STRING = "Connector"

    /* Must be in the same order as
       Constants.assetFilenames */
    val seasonColors = listOf(
        summer,
        lateSummer,
        autumn,
        winter,
        lateWinter,
        spring,
        lateSpring
    )
}