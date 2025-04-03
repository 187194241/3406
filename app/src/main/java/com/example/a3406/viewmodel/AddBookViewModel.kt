package com.example.a3406.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3406.model.Book
import com.example.a3406.repository.BookRepository
import kotlinx.coroutines.launch
import java.util.UUID
import android.util.Log

class AddBookViewModel(private val repository: BookRepository) : ViewModel() {
    fun addBook(
        title: String,
        author: String,
        description: String,
        progress: Int,
        rating: Int,
        review: String,
        genre: String
    ) {
        viewModelScope.launch {
            try {
                val book = Book(
                    id = UUID.randomUUID().toString(),
                    title = title,
                    author = author,
                    description = description,
                    coverUrl = null,
                    progress = progress,
                    rating = rating,
                    review = review,
                    genre = genre
                )
                repository.addBook(book)
                Log.d("AddBookViewModel", "Book added: $title")
            } catch (e: Exception) {
                Log.e("AddBookViewModel", "Failed to add book: ${e.message}")
            }
        }
    }
}