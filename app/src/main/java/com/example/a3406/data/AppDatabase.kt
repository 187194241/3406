package com.example.a3406.data

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.a3406.db.Database
import com.example.a3406.db.BookQueries

class AppDatabase(context: Context) {
    private val driver: SqlDriver = AndroidSqliteDriver(Database.Schema, context, "app_database.db")
    val bookQueries: BookQueries = Database(driver).bookQueries
}