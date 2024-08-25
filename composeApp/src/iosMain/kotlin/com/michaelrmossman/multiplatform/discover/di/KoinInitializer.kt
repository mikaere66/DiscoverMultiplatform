package com.michaelrmossman.multiplatform.discover.di

import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(
                databaseModule,
//                dataStoreModule,
                viewModelModule
            )
        }
    }
}