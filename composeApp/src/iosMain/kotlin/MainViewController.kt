package com.michaelrmossman.multiplatform.discover

import androidx.compose.ui.window.ComposeUIViewController
import com.michaelrmossman.multiplatform.discover.di.KoinInitializer

fun MainViewController() = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    DiscoverApp { _, content ->
        content()
    }
}