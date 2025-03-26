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
import com.example.a3406.viewmodel.BookListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BookListScreen(navController: NavController) {
    val viewModel: BookListViewModel = koinViewModel()
    val books = viewModel.books.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.navigate("add_book") }) {
                Text("Add Book")
            }
            Button(onClick = { navController.navigate("recommendations") }) {
                Text("Recommendations")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(books) { book ->
                BookCard(book = book) {
                    navController.navigate("book_detail/${book.id}")
                }
            }
        }
    }
}