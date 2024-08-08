package com.michaelrmossman.multiplatform.discover.databasee

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

class IOSDatabaseDriverFactory: DatabaseDriverFactory {

    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            DiscoverDatabase.Schema,"discover.db"
        )
    }
}