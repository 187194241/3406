package com.example.a3406.app_ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.a3406.model.Book
import com.example.a3406.viewmodel.BookDetailViewModel

@Composable
fun BookDetailScreen(bookId: String, navController: NavController, viewModel: BookDetailViewModel = viewModel()) {
    val book by viewModel.book.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(bookId) {
        viewModel.loadBook(bookId)
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        book?.let { b ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (b.coverUrl != null) {
                    AsyncImage(
                        model = b.coverUrl,
                        contentDescription = "Book Cover",
                        modifier = Modifier
                            .size(120.dp)
                            .clip(MaterialTheme.shapes.medium)
                    )
                }

                Text("Title: ${b.title}", style = MaterialTheme.typography.headlineMedium)
                Text("Author: ${b.author}", style = MaterialTheme.typography.bodyLarge)
                Text("Description: ${b.description}", style = MaterialTheme.typography.bodyMedium)
                Text("Genre: ${b.genre ?: "Not specified"}", style = MaterialTheme.typography.bodyMedium)

                Spacer(modifier = Modifier.height(16.dp))

                Text("Progress: ${b.progress}%")
                Slider(
                    value = b.progress.toFloat(),
                    onValueChange = { viewModel.updateProgress(it.toInt()) },
                    valueRange = 0f..100f,
                    modifier = Modifier.fillMaxWidth()
                )

                Text("Rating: ${b.rating}/5")
                Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    for (i in 1..5) {
                        Button(
                            onClick = { viewModel.updateRating(i) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (b.rating >= i) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text(i.toString())
                        }
                    }
                }

                var review by remember { mutableStateOf(b.review ?: "") }
                OutlinedTextField(
                    value = review,
                    onValueChange = {
                        review = it
                        viewModel.updateReview(it)
                    },
                    label = { Text("Review") },
                    modifier = Modifier.fillMaxWidth()
                )

                Button(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Back")
                }
            }
        } ?: run {
            Text("Book not found", modifier = Modifier.padding(padding))
        }
    }
}