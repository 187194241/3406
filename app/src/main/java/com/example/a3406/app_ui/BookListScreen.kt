package com.example.a3406.app_ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import com.example.a3406.model.Book
import androidx.compose.foundation.lazy.items


@Composable
fun BookListScreen(navController: NavController, books: List<Book>) {
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
                BookCard(book = book)
            }
        }
    }
}