package com.example.a3406.app_ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a3406.model.Book
import com.example.a3406.repository.Result
import com.example.a3406.viewmodel.RecommendationsViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RecommendationsScreen(navController: NavController) {
    val viewModel: RecommendationsViewModel = koinViewModel()
    val recommendedBooksState = viewModel.recommendedBooks.collectAsState()
    val recommendedBooks = recommendedBooksState.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Recommended Books", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        when (recommendedBooks) {
            is Result.Loading -> {
                CircularProgressIndicator(modifier = Modifier.fillMaxSize())
            }
            is Result.Success<List<Book>> -> {
                val books = recommendedBooks.data
                LazyColumn {
                    items(books) { book ->
                        BookCard(book = book) {}
                    }
                }
            }
            is Result.Error -> {
                Text(
                    text = "Error: ${recommendedBooks.message}",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}