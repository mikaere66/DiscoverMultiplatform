package com.michaelrmossman.multiplatform.discover.utils

import android.os.Build
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
actual fun getLocalTime(): String {
    // Returns e.g. 20:37:00.866
    val sdf = SimpleDateFormat("H:m:s.S", Locale.getDefault())
    return sdf.format(Date()).toString()
}
