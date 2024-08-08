package com.michaelrmossman.multiplatform.discover.di

import android.content.Context
import com.michaelrmossman.multiplatform.discover.di.databaseModule
import com.michaelrmossman.multiplatform.discover.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

actual class KoinInitializer(
    private val context: Context
) {
    actual fun init() {
        startKoin {
            androidContext(context)
            modules(databaseModule, viewModelModule)
        }
    }
}