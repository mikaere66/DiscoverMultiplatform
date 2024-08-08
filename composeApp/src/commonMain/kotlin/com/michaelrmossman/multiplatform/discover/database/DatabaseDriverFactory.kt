package com.michaelrmossman.multiplatform.discover.database

import app.cash.sqldelight.db.SqlDriver

interface DatabaseDriverFactory {

    fun createDriver(): SqlDriver
}