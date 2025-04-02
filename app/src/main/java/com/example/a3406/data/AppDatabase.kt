package com.example.a3406.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.a3406.db.Database
import com.example.a3406.db.BookQueries

class AppDatabase(context: Context) {
    private val driver: SqlDriver
    val bookQueries: BookQueries

    init {
        try {
            driver = AndroidSqliteDriver(Database.Schema, context, "app_database.db")
            bookQueries = Database(driver).bookQueries
        } catch (e: Exception) {
            throw RuntimeException("Failed to initialize database: ${e.message}", e)
        }
    }

    fun close() {
        driver.close()
    }
}