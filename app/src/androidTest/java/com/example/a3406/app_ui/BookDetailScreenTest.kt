package com.example.a3406.app_ui

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class BookDetailScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun displaysSimpleText() {
        composeTestRule.setContent {
            Text("Hello, Book Tracker!")
        }

        composeTestRule.onNodeWithText("Hello, Book Tracker!").assertIsDisplayed()
    }
}