package com.michaelrmossman.multiplatform.discover.utils

import android.os.Build
import com.michaelrmossman.multiplatform.discover.utils.Constants.FAVE_DATE_FORMAT
import com.michaelrmossman.multiplatform.discover.utils.Constants.FAVE_TIME_FORMAT_LONG
import com.michaelrmossman.multiplatform.discover.utils.Constants.FAVE_TIME_FORMAT_SHORT
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.TimeZone
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

// https://docs.oracle.com/javase/8/docs/api/java/text/SimpleDateFormat.html
actual fun getLocalDate(): String {
    // Returns e.g. 18 Aug, 2024
    val sdf = SimpleDateFormat(FAVE_DATE_FORMAT, Locale.getDefault())
    return sdf.format(Date()).toString()
}

actual fun getLocalTime(short: Boolean): String {
    val format = when (short) {
        true -> FAVE_TIME_FORMAT_SHORT
        else -> FAVE_TIME_FORMAT_LONG
    }
    /* Returns e.g.
       short "8:37 pm" or
       long: "20:37:00.866" */
    val sdf = SimpleDateFormat(format, Locale.getDefault())
    return sdf.format(Date()).toString()
}