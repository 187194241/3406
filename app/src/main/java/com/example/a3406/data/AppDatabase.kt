package com.example.a3406.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.a3406.db.Database
import com.example.a3406.db.BookQueries

class AppDatabase private constructor(private val driver: SqlDriver) {
    val bookQueries: BookQueries

    init {
        try {
            bookQueries = Database(driver).bookQueries
        } catch (e: Exception) {
            throw RuntimeException("Failed to initialize database: ${e.message}", e)
        }
    }

    constructor(context: Context) : this(
        AndroidSqliteDriver(Database.Schema, context, "app_database.db")
    )

    fun close() {
        driver.close()
    }
}