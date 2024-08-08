package com.michaelrmossman.multiplatform.discover

import android.app.Application
import com.michaelrmossman.multiplatform.discover.di.KoinInitializer

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        KoinInitializer(applicationContext).init()
    }
}