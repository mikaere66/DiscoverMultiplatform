package com.michaelrmossman.multiplatform.discover.navigation

import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.michaelrmossman.multiplatform.discover.presentation.MainListEvent
import kotlinx.coroutines.android.awaitFrame
import kotlinx.coroutines.launch

@SuppressLint("RestrictedApi")
@Composable
actual fun OnBackHandler(
    currentDestination: NavigationType,
    onEvent: (MainListEvent) -> Unit,
    startDestination: NavigationType
) {
    // https://stackoverflow.com/questions/69151521/how-to-override-the-system-onbackpress-in-jetpack-compose/69151539#69151539
    var backPressHandled by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val onBackPressedDispatcher =
        LocalOnBackPressedDispatcherOwner
            .current?.onBackPressedDispatcher

    BackHandler(
        enabled = !backPressHandled
        &&
        startDestination.name != currentDestination.name
    ) {
        backPressHandled = true
        coroutineScope.launch {
            awaitFrame()
            onEvent(
                MainListEvent.SetCurrentNavType(
                    navType = startDestination
                )
            )
            onBackPressedDispatcher?.onBackPressed()
            backPressHandled = false
        }
    }
}