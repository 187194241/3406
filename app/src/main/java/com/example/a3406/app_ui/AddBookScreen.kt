package com.example.a3406.app_ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.a3406.viewmodel.AddBookViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddBookScreen(navController: NavController) {
    val viewModel: AddBookViewModel = koinViewModel()
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
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        TextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        TextField(value = author, onValueChange = { author = it }, label = { Text("Author") })
        TextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
        TextField(value = progress, onValueChange = { progress = it }, label = { Text("Progress (0-100)") })
        TextField(value = rating, onValueChange = { rating = it }, label = { Text("Rating (1-5)") })
        TextField(value = review, onValueChange = { review = it }, label = { Text("Review") })
        TextField(value = genre, onValueChange = { genre = it }, label = { Text("Genre") })

        Button(onClick = {
            viewModel.addBook(
                title,
                author,
                description,
                progress.toIntOrNull() ?: 0,
                rating.toIntOrNull() ?: 0,
                review,
                genre
            )
            navController.popBackStack()
        }) {
            Text("Add Book")
        }
    }
}

