package com.michaelrmossman.multiplatform.discover.di

import com.michaelrmossman.multiplatform.discover.di.databaseModule
import com.michaelrmossman.multiplatform.discover.di.viewModelModule
import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(databaseModule, viewModelModule)
        }
    }
}