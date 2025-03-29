package com.example.a3406.di

import android.content.Context
import com.example.a3406.data.AppDatabase
import com.example.a3406.repository.BookRepository
import org.koin.dsl.module

val appModule = module {
    single { (context: Context) -> AppDatabase(context) }
    single { BookRepository(get()) }
}