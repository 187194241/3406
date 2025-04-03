package com.example.a3406.repository

import com.example.a3406.data.AppDatabase
import com.example.a3406.db.Book
import com.example.a3406.db.BookQueries
import com.example.a3406.model.Book as ModelBook
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue

class BookRepositoryTest {
    private lateinit var bookQueries: BookQueries
    private lateinit var database: AppDatabase
    private lateinit var repository: BookRepository

    @Before
    fun setup() {
        bookQueries = mockk(relaxed = true)
        database = mockk(relaxed = true) {
            every { bookQueries } returns this@BookRepositoryTest.bookQueries
        }
        repository = BookRepository(database)
    }

    @Test
    fun `addBook should call insertBook on bookQueries`() = runTest {
        // Given
        val book = ModelBook(
            id = "book1",
            title = "Test Book",
            author = "Test Author",
            description = "Test Description",
            coverUrl = null,
            progress = 0,
            rating = 0,
            review = null,
            genre = "fiction"
        )

        // When
        repository.addBook(book)

        // Then
        verify {
            bookQueries.insertBook(
                id = book.id,
                title = book.title,
                author = book.author,
                description = book.description,
                coverUrl = book.coverUrl,
                progress = book.progress.toLong(),
                rating = book.rating.toLong(),
                review = book.review,
                genre = book.genre
            )
        }
    }

    @Test
    fun `getAllBooks should return empty list when no books exist`() = runTest {
        // Given
        every { bookQueries.selectAllBooks().executeAsList() } returns emptyList()

        // When
        val books = repository.getAllBooks().first()

        // Then
        assertTrue(books.isEmpty())
    }

    @Test
    fun `getAllBooks should return books when they exist`() = runTest {
        // Given
        val dbBook = Book(
            id = "book1",
            title = "Test Book",
            author = "Test Author",
            description = "Test Description",
            coverUrl = null,
            progress = 0,
            rating = 0,
            review = null,
            genre = "fiction"
        )
        every { bookQueries.selectAllBooks().executeAsList() } returns listOf(dbBook)

        // When
        val books = repository.getAllBooks().first()

        // Then
        assertEquals(1, books.size)
        assertEquals(dbBook.id, books[0].id)
        assertEquals(dbBook.title, books[0].title)
    }
}