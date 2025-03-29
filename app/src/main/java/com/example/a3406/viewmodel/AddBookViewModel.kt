package com.example.a3406.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3406.model.Book
import com.example.a3406.repository.BookRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID

class AddBookViewModel : ViewModel(), KoinComponent {
    private val repository: BookRepository by inject()

    fun addBook(
        title: String,
        author: String,
        description: String,
        progress: Int,
        rating: Int,
        review: String,
        genre: String
    ) {
        val book = Book(
            id = UUID.randomUUID().toString(),
            title = title,
            author = author,
            description = description,
            progress = progress,
            rating = rating,
            review = review,
            genre = genre
        )
        viewModelScope.launch {
            repository.addBook(book)
        }
    }
}