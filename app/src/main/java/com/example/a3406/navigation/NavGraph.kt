package com.example.a3406.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.a3406.app_ui.AddBookScreen
import com.example.a3406.app_ui.BookDetailScreen
import com.example.a3406.app_ui.BookListScreen
import com.example.a3406.app_ui.RecommendationsScreen

@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "book_list") {
        composable("book_list") {
            BookListScreen(navController)
        }
        composable("add_book") {
            AddBookScreen(navController)
        }
        composable("book_detail/{bookId}") { backStackEntry ->
            val bookId = backStackEntry.arguments?.getString("bookId") ?: ""
            BookDetailScreen(navController, bookId)
        }
        composable("recommendations") {
            RecommendationsScreen(navController)
        }
    }
}