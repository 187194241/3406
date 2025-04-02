package com.example.a3406.app_ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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

    if (book == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("Book not found or loading...", modifier = Modifier.padding(16.dp))
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = book!!.title, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Author: ${book!!.author}")
            Text(text = "Description: ${book!!.description}")

            var progress by remember { mutableStateOf(book!!.progress.toString()) }
            TextField(
                value = progress,
                onValueChange = {
                    progress = it
                    val newProgress = it.toIntOrNull() ?: 0
                    if (newProgress in 0..100) viewModel.updateProgress(newProgress)
                },
                label = { Text("Progress (0-100)") }
            )

            var rating by remember { mutableStateOf(book!!.rating.toString()) }
            TextField(
                value = rating,
                onValueChange = {
                    rating = it
                    val newRating = it.toIntOrNull() ?: 0
                    if (newRating in 1..5) viewModel.updateRating(newRating)
                },
                label = { Text("Rating (1-5)") }
            )

            var review by remember { mutableStateOf(book!!.review ?: "") }
            TextField(
                value = review,
                onValueChange = {
                    review = it
                    viewModel.updateReview(it)
                },
                label = { Text("Review") }
            )
        }
    }
}