package com.michaelrmossman.multiplatform.discover.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.LocalTime
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.TimeZone

// https://medium.com/programming-with-swift/get-hours-minutes-and-seconds-from-date-with-swift-d11bdac4368c
// https://stackoverflow.com/questions/48371082/swift-dateformatter-optional-milliseconds
actual fun getLocalTime(): String {
    // Returns e.g. 20:37:00.866
    let date = Date()
    var calendar = Calendar.current
    let hour = calendar.component(.hour, from: date)
    let minute = calendar.component(.minute, from: date)
    let second = calendar.component(.second, from: date)
    return "\(hour):\(minute):\(second)"
}
