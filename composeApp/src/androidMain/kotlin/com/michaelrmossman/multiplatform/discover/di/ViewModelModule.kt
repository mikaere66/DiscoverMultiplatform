package com.michaelrmossman.multiplatform.discover.di

import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

actual val viewModelModule = module {
    viewModelOf(::MainViewModel)
}