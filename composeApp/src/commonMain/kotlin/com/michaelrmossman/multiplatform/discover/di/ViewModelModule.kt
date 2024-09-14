package com.michaelrmossman.multiplatform.discover.di

import com.composegears.tiamat.koin.tiamatViewModelOf
import com.michaelrmossman.multiplatform.discover.presentation.MainViewModel
import org.koin.dsl.module

val viewModelModule = module {
    tiamatViewModelOf(::MainViewModel)
}