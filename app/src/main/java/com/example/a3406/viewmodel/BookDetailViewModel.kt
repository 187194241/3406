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

    private val _pendingProgress = MutableStateFlow<Int?>(null)
    val pendingProgress: StateFlow<Int?> = _pendingProgress

    private val _pendingRating = MutableStateFlow<Int?>(null)
    val pendingRating: StateFlow<Int?> = _pendingRating

    private val _pendingReview = MutableStateFlow<String?>(null)
    val pendingReview: StateFlow<String?> = _pendingReview

    fun loadBook(bookId: String) {
        viewModelScope.launch {
            repository.getBookById(bookId)
                .stateIn(viewModelScope, SharingStarted.Lazily, null)
                .collect { book ->
                    _book.value = book

                    _pendingProgress.value = book?.progress
                    _pendingRating.value = book?.rating
                    _pendingReview.value = book?.review
                }
        }
    }

    fun updatePendingProgress(progress: Int) {
        _pendingProgress.value = progress
    }

    fun updatePendingRating(rating: Int) {
        _pendingRating.value = rating
    }

    fun updatePendingReview(review: String) {
        _pendingReview.value = review
    }

    fun confirmChanges() {
        _book.value?.let { currentBook ->
            val updatedBook = currentBook.copy(
                progress = _pendingProgress.value ?: currentBook.progress,
                rating = _pendingRating.value ?: currentBook.rating,
                review = _pendingReview.value ?: currentBook.review
            )
            _book.value = updatedBook
            viewModelScope.launch {
                repository.updateBook(updatedBook)
            }
        }
    }

    fun deleteBook() {
        _book.value?.let { book ->
            viewModelScope.launch {
                repository.deleteBook(book.id)
                _book.value = null
            }
        }
    }
}