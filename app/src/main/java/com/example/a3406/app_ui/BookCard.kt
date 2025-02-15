package com.example.a3406.app_ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme
import coil.compose.AsyncImage
import com.example.a3406.model.Book
import androidx.compose.ui.draw.clip

@Composable
fun BookCard(book: Book) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .padding(vertical = 8.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            if (book.coverUrl != null) {
                AsyncImage(
                    model = book.coverUrl,
                    contentDescription = "Book Cover",
                    modifier = Modifier
                        .width(80.dp)
                        .height(120.dp)
                        .clip(MaterialTheme.shapes.medium)
                )
                Spacer(modifier = Modifier.width(16.dp))
            }

            Column(modifier = Modifier.weight(1f)) {
                Text("Title: ${book.title}", style = MaterialTheme.typography.headlineSmall)
                Text("Author: ${book.author}", style = MaterialTheme.typography.bodyMedium)
                Text("Description: ${book.description}", style = MaterialTheme.typography.bodySmall)
            }
        }
    }
}