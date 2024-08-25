package com.michaelrmossman.multiplatform.discover.utils

import com.michaelrmossman.multiplatform.discover.utils.Constants.FAVE_DATE_FORMAT
import com.michaelrmossman.multiplatform.discover.utils.Constants.FAVE_TIME_FORMAT_LONG
import com.michaelrmossman.multiplatform.discover.utils.Constants.FAVE_TIME_FORMAT_SHORT
import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSTimeZone
import platform.Foundation.localTimeZone
import platform.Foundation.timeIntervalSince1970

// https://medium.com/@adman.shadman/implementing-ios-android-date-time-utilities-in-kotlin-multiplatform-285d03d5e877
actual fun getLocalDate(): String {
    // Returns e.g. 18 Aug, 2024
    val timeStamp = (NSDate().timeIntervalSince1970 * 1000).toLong()
    // val outputFormat = "d MMM, y"
    val formatter = NSDateFormatter().apply {
        dateFormat = FAVE_DATE_FORMAT
        timeZone = NSTimeZone.localTimeZone
    }
    val date = NSDate(timeStamp.toDouble() / 1000)
    return formatter.stringFromDate(date)
}

actual fun getLocalTime(short: Boolean): String {
    val format = when (short) {
        true -> FAVE_TIME_FORMAT_SHORT
        else -> FAVE_TIME_FORMAT_LONG
    }
    /* Returns e.g.
       short "8:37 pm" or
       long: "20:37:00.866" */
    val timeStamp = (NSDate().timeIntervalSince1970 * 1000).toLong()
    // val outputFormat = "H:m:s.S"
    val formatter = NSDateFormatter().apply {
        dateFormat = format
        timeZone = NSTimeZone.localTimeZone
    }
    val date = NSDate(timeStamp.toDouble() / 1000)
    return formatter.stringFromDate(date)
}