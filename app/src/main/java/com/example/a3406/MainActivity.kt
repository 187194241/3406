package com.example.a3406

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.a3406.navigation.SetupNavGraph
import com.example.a3406.ui.theme.BookAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookAppTheme {
                BookTrackerApp()
            }
        }
    }
}

@Composable
fun BookTrackerApp() {
    val navController = rememberNavController()
    SetupNavGraph(navController = navController)
}