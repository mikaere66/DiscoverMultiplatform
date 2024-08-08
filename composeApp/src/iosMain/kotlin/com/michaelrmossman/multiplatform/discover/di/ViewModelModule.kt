package com.michaelrmossman.multiplatform.discover.di

import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual val viewModelModule = module {
    singleOf(::MainViewModel)
}