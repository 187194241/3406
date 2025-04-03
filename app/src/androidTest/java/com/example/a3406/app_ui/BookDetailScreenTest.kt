package com.example.a3406.app_ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.NavController
import com.example.a3406.MainActivity
import com.example.a3406.model.Book
import com.example.a3406.viewmodel.BookDetailViewModel
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class BookDetailScreenTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    private val mockViewModel: BookDetailViewModel = mockk(relaxed = true)
    private val mockNavController: NavController = mockk(relaxed = true)

    @Before
    fun setup() {
        // Mock ViewModel 数据
        val bookFlow = MutableStateFlow<Book?>(Book(
            id = "book1",
            title = "Test Book",
            author = "Test Author",
            description = "Test Description",
            coverUrl = null,
            progress = 50,
            rating = 4,
            review = "Great book!",
            genre = "fiction"
        ))
        every { mockViewModel.book } returns bookFlow
        every { mockViewModel.pendingProgress } returns MutableStateFlow(50)
        every { mockViewModel.pendingRating } returns MutableStateFlow(4)
        every { mockViewModel.pendingReview } returns MutableStateFlow("Great book!")
    }

    @Test
    fun displaysBookDetailsCorrectly() {
        composeTestRule.setContent {
            BookDetailScreen(navController = mockNavController, bookId = "book1")
        }

        // 验证书籍详情是否正确显示
        composeTestRule.onNodeWithText("Test Book").assertIsDisplayed()
        composeTestRule.onNodeWithText("Author: Test Author").assertIsDisplayed()
        composeTestRule.onNodeWithText("Description: Test Description").assertIsDisplayed()
        composeTestRule.onNodeWithText("Progress (0-100)").assertIsDisplayed()
        composeTestRule.onNodeWithText("50").assertIsDisplayed()
        composeTestRule.onNodeWithText("Rating (1-5)").assertIsDisplayed()
        composeTestRule.onNodeWithText("4").assertIsDisplayed()
        composeTestRule.onNodeWithText("Review").assertIsDisplayed()
        composeTestRule.onNodeWithText("Great book!").assertIsDisplayed()
    }
}