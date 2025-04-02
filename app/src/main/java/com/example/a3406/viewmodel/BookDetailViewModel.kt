package com.example.a3406.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3406.model.Book
import com.example.a3406.repository.BookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class BookDetailViewModel(private val repository: BookRepository) : ViewModel() {
    private val _book = MutableStateFlow<Book?>(null)
    val book: StateFlow<Book?> = _book

    fun loadBook(bookId: String) {
        viewModelScope.launch {
            repository.getBookById(bookId)
                .stateIn(viewModelScope, SharingStarted.Lazily, null)
                .collect { book ->
                    _book.value = book
                }
        }
    }

    fun updateProgress(progress: Int) {
        _book.value?.let { currentBook ->
            val updatedBook = currentBook.copy(progress = progress)
            _book.value = updatedBook
            viewModelScope.launch {
                repository.updateBook(updatedBook)
            }
        }
    }

    fun updateRating(rating: Int) {
        _book.value?.let { currentBook ->
            val updatedBook = currentBook.copy(rating = rating)
            _book.value = updatedBook
            viewModelScope.launch {
                repository.updateBook(updatedBook)
            }
        }
    }

    fun updateReview(review: String) {
        _book.value?.let { currentBook ->
            val updatedBook = currentBook.copy(review = review)
            _book.value = updatedBook
            viewModelScope.launch {
                repository.updateBook(updatedBook)
            }
        }
    }
}