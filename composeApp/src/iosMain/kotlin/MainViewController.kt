package com.michaelrmossman.multiplatform.discover

import androidx.compose.ui.window.ComposeUIViewController
import com.michaelrmossman.multiplatform.discover.di.KoinInitializer
import com.michaelrmossman.multiplatform.discover.entry.MainEntryPoint

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    MainEntryPoint()
}