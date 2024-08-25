package com.michaelrmossman.multiplatform.discover.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

// https://stackoverflow.com/questions/67768746/chaining-modifier-based-on-certain-conditions-in-android-compose
// Adds a modifier (or not) based on a condition
fun Modifier.conditional(
    condition: Boolean,
    modifier: Modifier.() -> Modifier
) : Modifier {
    return if (condition) {
        then(modifier(Modifier))
    } else {
        this
    }
}
