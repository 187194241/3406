package com.example.a3406.app_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.a3406.model.Book
import com.example.a3406.viewmodel.BookListViewModel

@Composable
fun BookListScreen(navController: NavController, viewModel: BookListViewModel = viewModel()) {
    val books by viewModel.books.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Book List", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("addBook") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add New Book")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("recommendations") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("See Recommendations")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(books) { book ->
                BookCard(
                    book = book,
                    modifier = Modifier.clickable { navController.navigate("bookDetail/${book.id}") }
                )
            }
        }
    }
}

@Composable
fun BookCard(book: Book, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            if (book.coverUrl != null) {
                AsyncImage(
                    model = book.coverUrl,
                    contentDescription = "Book Cover",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
                Text("Title: ${book.title}", style = MaterialTheme.typography.headlineSmall)
                Text("Author: ${book.author}", style = MaterialTheme.typography.bodyMedium)
                Text("Progress: ${book.progress}%", style = MaterialTheme.typography.bodySmall)
                Text("Rating: ${book.rating}/5", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}