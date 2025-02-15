package com.example.a3406.app_ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RecommendationsScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Recommended Books", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        // 模拟书籍列表
        val books = List(5) { index -> "Book ${index + 1}" }

        LazyColumn {
            itemsIndexed(books) { index, book ->
                Column(modifier = Modifier.padding(vertical = 8.dp)) {
                    Text(book, style = MaterialTheme.typography.bodyLarge)

                    // 书细节按钮
                    Button(
                        onClick = {
                            // 导航到书籍详细信息页面，传递书籍 ID
                            navController.navigate("bookDetail/$index")
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("View Details")
                    }

                    // 添加分隔线
                    Divider(modifier = Modifier.padding(vertical = 8.dp))
                }
            }
        }
    }
}