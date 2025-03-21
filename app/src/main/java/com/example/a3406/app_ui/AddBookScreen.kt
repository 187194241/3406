package com.example.a3406.app_ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.a3406.viewmodel.AddBookViewModel

@Composable
fun AddBookScreen(navController: NavController, viewModel: AddBookViewModel = viewModel()) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var progress by remember { mutableStateOf("") }
    var rating by remember { mutableStateOf("") }
    var review by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .imePadding(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text("Add a New Book", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = progress,
            onValueChange = { if (it.length <= 3) progress = it },
            label = { Text("Progress (%)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = rating,
            onValueChange = { if (it.length <= 1) rating = it },
            label = { Text("Rating (1-5)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = review,
            onValueChange = { review = it },
            label = { Text("Review") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = genre,
            onValueChange = { genre = it },
            label = { Text("Genre") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val progressInt = progress.toIntOrNull() ?: 0
                val ratingInt = rating.toIntOrNull() ?: 0
                if (progressInt in 0..100 && ratingInt in 0..5) {
                    viewModel.addBook(title, author, description, progressInt, ratingInt, review, genre)
                    navController.popBackStack()
                } else {
                    // Show error (e.g., using a Snackbar, which we'll add later)
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}