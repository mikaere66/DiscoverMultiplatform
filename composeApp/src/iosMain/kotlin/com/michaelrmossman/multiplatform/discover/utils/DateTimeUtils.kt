package com.michaelrmossman.multiplatform.discover.utils

import platform.Foundation.NSDate
import platform.Foundation.NSDateFormatter
import platform.Foundation.NSTimeZone
import platform.Foundation.localTimeZone
import platform.Foundation.timeIntervalSince1970

// https://medium.com/@adman.shadman/implementing-ios-android-date-time-utilities-in-kotlin-multiplatform-285d03d5e877
actual fun getLocalTime(): String {
    // Returns e.g. 20:37:00.866
    val timeStamp = (NSDate().timeIntervalSince1970 * 1000).toLong()
    val outputFormat = "H:m:s.S"
    val formatter = NSDateFormatter().apply {
        dateFormat = outputFormat
        timeZone = NSTimeZone.localTimeZone
    }
    val date = NSDate(timeStamp.toDouble() / 1000)
    return formatter.stringFromDate(date)
}
