package com.michaelrmossman.multiplatform.discover

import androidx.compose.ui.window.ComposeUIViewController
import com.michaelrmossman.multiplatform.discover.di.KoinInitializer
import com.michaelrmossman.multiplatform.discover.screens.MainScreen

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    MainScreen()
}