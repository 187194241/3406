package com.example.a3406.app_ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    val pendingProgress by viewModel.pendingProgress.collectAsState()
    val pendingRating by viewModel.pendingRating.collectAsState()
    val pendingReview by viewModel.pendingReview.collectAsState()

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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = book!!.title, style = MaterialTheme.typography.headlineMedium)
            Text(text = "Author: ${book!!.author}")
            Text(text = "Description: ${book!!.description}")

            var progress by remember { mutableStateOf(pendingProgress?.toString() ?: book!!.progress.toString()) }
            TextField(
                value = progress,
                onValueChange = {
                    progress = it
                    val newProgress = it.toIntOrNull() ?: 0
                    if (newProgress in 0..100) viewModel.updatePendingProgress(newProgress)
                },
                label = { Text("Progress (0-100)") }
            )

            var rating by remember { mutableStateOf(pendingRating?.toString() ?: book!!.rating.toString()) }
            TextField(
                value = rating,
                onValueChange = {
                    rating = it
                    val newRating = it.toIntOrNull() ?: 0
                    if (newRating in 1..5) viewModel.updatePendingRating(newRating)
                },
                label = { Text("Rating (1-5)") }
            )

            var review by remember { mutableStateOf(pendingReview ?: book!!.review ?: "") }
            TextField(
                value = review,
                onValueChange = {
                    review = it
                    viewModel.updatePendingReview(it)
                },
                label = { Text("Review") }
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {
                    viewModel.confirmChanges()
                    navController.popBackStack()
                }) {
                    Text("Confirm")
                }

                Button(
                    onClick = {
                        viewModel.deleteBook()
                        navController.popBackStack()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Delete")
                }
            }
        }
    }
}