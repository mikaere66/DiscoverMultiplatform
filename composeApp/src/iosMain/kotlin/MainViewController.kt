package com.michaelrmossman.multiplatform.discover

import androidx.compose.ui.window.ComposeUIViewController
import com.michaelrmossman.multiplatform.discover.di.KoinInitializer
import com.michaelrmossman.multiplatform.discover.DiscoverApp

fun MainViewController(viewModel: MainViewModel) = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    DiscoverApp { _, content ->
        content()
    }
}