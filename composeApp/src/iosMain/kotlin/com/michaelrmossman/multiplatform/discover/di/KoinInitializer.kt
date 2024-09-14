package com.michaelrmossman.multiplatform.discover.di

import com.rickclephas.kmp.nativecoroutines.NativeCoroutines
import org.koin.core.context.startKoin

actual class KoinInitializer {
    actual fun init() {
        startKoin {
            modules(
                databaseModule,
                viewModelModule
            )
        }
    }
}
