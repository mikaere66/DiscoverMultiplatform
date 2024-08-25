package com.michaelrmossman.multiplatform.discover.di

import com.michaelrmossman.multiplatform.discover.database.IOSDatabaseDriverFactory
import com.michaelrmossman.multiplatform.discover.database.DatabaseImpl
import com.michaelrmossman.multiplatform.discover.utils.JsonUtils
import org.koin.dsl.module

actual val databaseModule = module {

    single<JsonUtils> { JsonUtils() }

    single<DatabaseImpl> {
        DatabaseImpl(
            databaseDriverFactory = IOSDatabaseDriverFactory(
            ) , json = get()
        )
    }
}