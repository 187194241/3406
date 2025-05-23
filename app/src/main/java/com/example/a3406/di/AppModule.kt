package com.example.a3406.di

import com.example.a3406.data.AppDatabase
import com.example.a3406.repository.BookRepository
import com.example.a3406.viewmodel.AddBookViewModel
import com.example.a3406.viewmodel.BookDetailViewModel
import com.example.a3406.viewmodel.BookListViewModel
import com.example.a3406.viewmodel.RecommendationsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { AppDatabase(get()) }
    single { BookRepository(get()) }

    viewModel { BookListViewModel(get()) }
    viewModel { AddBookViewModel(get()) }
    viewModel { RecommendationsViewModel(get()) }
    viewModel { BookDetailViewModel(get()) }
}