package com.michaelrmossman.multiplatform.discover.navigation

import androidx.compose.runtime.Composable
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent

@Composable
actual fun OnBackHandler(
    currentDestination: NavigationType,
    onEvent: (MainListEvent) -> Unit,
    startDestination: NavigationType
) {}