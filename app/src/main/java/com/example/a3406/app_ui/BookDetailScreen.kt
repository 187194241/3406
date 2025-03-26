package com.example.a3406.app_ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a3406.viewmodel.BookDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookDetailScreen(navController: NavController, bookId: String) {
    val viewModel: BookDetailViewModel = koinViewModel()
    LaunchedEffect(bookId) {
        viewModel.loadBook(bookId)
    }

    val book by viewModel.book.collectAsState()

    book?.let { currentBook ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = currentBook.title, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Author: ${currentBook.author}")
            Text(text = "Description: ${currentBook.description}")

            var progress by remember { mutableStateOf(currentBook.progress.toString()) }
            TextField(
                value = progress,
                onValueChange = {
                    progress = it
                    val newProgress = it.toIntOrNull() ?: 0
                    if (newProgress in 0..100) viewModel.updateProgress(newProgress)
                },
                label = { Text("Progress (0-100)") }
            )

            var rating by remember { mutableStateOf(currentBook.rating.toString()) }
            TextField(
                value = rating,
                onValueChange = {
                    rating = it
                    val newRating = it.toIntOrNull() ?: 0
                    if (newRating in 1..5) viewModel.updateRating(newRating)
                },
                label = { Text("Rating (1-5)") }
            )

            var review by remember { mutableStateOf(currentBook.review ?: "") }
            TextField(
                value = review,
                onValueChange = {
                    review = it
                    viewModel.updateReview(it)
                },
                label = { Text("Review") }
            )
        }
    } ?: run {
        Text("Loading book details...", modifier = Modifier.padding(16.dp))
    }
}