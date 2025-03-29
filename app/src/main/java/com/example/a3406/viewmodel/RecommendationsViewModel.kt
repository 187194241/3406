package com.example.a3406.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3406.model.Book
import com.example.a3406.repository.BookRepository
import com.example.a3406.repository.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecommendationsViewModel : ViewModel(), KoinComponent {
    private val repository: BookRepository by inject()

    private val _recommendedBooks = MutableStateFlow<Result<List<Book>>>(Result.Loading)
    val recommendedBooks: StateFlow<Result<List<Book>>> = _recommendedBooks

    init {
        fetchRecommendations()
    }

    private fun fetchRecommendations() {
        viewModelScope.launch {
            _recommendedBooks.value = Result.Loading
            val genre = "fiction"
            _recommendedBooks.value = repository.getRecommendations(genre)
        }
    }

    fun fetchRecommendationsByGenreFromLocalBooks() {
        viewModelScope.launch {
            _recommendedBooks.value = Result.Loading
            val localBooksFlow = repository.getAllBooks().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
            localBooksFlow.collect { books ->
                val genre = books.firstOrNull()?.genre ?: "fiction"
                _recommendedBooks.value = repository.getRecommendations(genre)
            }
        }
    }
}