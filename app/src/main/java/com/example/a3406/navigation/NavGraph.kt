package com.example.a3406.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a3406.app_ui.BookListScreen
import com.example.a3406.app_ui.AddBookScreen
import com.example.a3406.app_ui.RecommendationsScreen
import com.example.a3406.app_ui.BookDetailScreen
import androidx.compose.ui.Modifier
import com.example.a3406.model.Book


@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    val books = listOf(
        Book(id = "1", title = "1984", author = "George Orwell", description = "A dystopian novel.", coverUrl = null),
        Book(id = "2", title = "Brave New World", author = "Aldous Huxley", description = "A futuristic society.", coverUrl = null),
        Book(id = "3", title = "Fahrenheit 451", author = "Ray Bradbury", description = "A book-burning dystopia.", coverUrl = null)
    )

    NavHost(navController = navController, startDestination = "bookList") {
        composable("bookList") {
            BookListScreen(navController = navController, books = books)

        }
        composable("addBook") {
            AddBookScreen(navController = navController)
        }
        composable("recommendations") {
            RecommendationsScreen(navController = navController)
        }
        // 添加书籍详细信息页面的路由
        composable("bookDetail/{bookId}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            BookDetailScreen(bookId = bookId)
        }
    }
}