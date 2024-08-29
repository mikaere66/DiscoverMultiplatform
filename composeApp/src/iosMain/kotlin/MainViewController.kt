package com.michaelrmossman.multiplatform.discover

import androidx.compose.ui.window.ComposeUIViewController
import com.michaelrmossman.multiplatform.discover.di.KoinInitializer
import com.michaelrmossman.multiplatform.discover.entry.MainEntryPoint
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel

fun MainViewController(viewModel: MainViewModel) = ComposeUIViewController(
    configure = {
        KoinInitializer().init()
    }
) {
    MainEntryPoint(viewModel)
}