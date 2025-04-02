package com.example.a3406.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3406.model.Book
import com.example.a3406.repository.BookRepository
import com.example.a3406.repository.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RecommendationsViewModel(private val repository: BookRepository) : ViewModel() {
    private val _recommendedBooks = MutableStateFlow<Result<List<Book>>>(Result.Loading)
    val recommendedBooks: StateFlow<Result<List<Book>>> = _recommendedBooks

    fun fetchRecommendationsByGenreFromLocalBooks() {
        viewModelScope.launch {
            _recommendedBooks.value = Result.Loading
            try {
                val localBooks = repository.getAllBooks().stateIn(viewModelScope, SharingStarted.Lazily, emptyList()).value
                val genre = localBooks.firstOrNull()?.genre ?: "fiction"
                _recommendedBooks.value = repository.getRecommendations(genre)
            } catch (e: Exception) {
                _recommendedBooks.value = Result.Error(e.message ?: "Failed to fetch recommendations")
            }
        }
    }
}