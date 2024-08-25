package com.michaelrmossman.multiplatform.discover.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getDayNightIconColor(): Color {
    return when (isSystemInDarkTheme()) {
        true -> Color.White
        else -> Color.Black
    }
}