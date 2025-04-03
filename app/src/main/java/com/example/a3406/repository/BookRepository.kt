package com.example.a3406.repository

import com.example.a3406.data.AppDatabase
import com.example.a3406.db.Book
import com.example.a3406.model.Book as ModelBook
import com.example.a3406.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class BookRepository(
    database: AppDatabase
) {
    private val bookQueries = database.bookQueries
    private val bookApiService = RetrofitClient.bookApiService

    fun getAllBooks(): Flow<List<ModelBook>> = flow {
        val books = bookQueries.selectAllBooks().executeAsList()
        emit(books.map { toModelBook(it) })
    }.flowOn(Dispatchers.IO)

    fun getBookById(id: String): Flow<ModelBook?> = flow {
        val book = bookQueries.selectBookById(id).executeAsOneOrNull()?.let { toModelBook(it) }
        emit(book)
    }.flowOn(Dispatchers.IO)

    fun addBook(book: ModelBook) {
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

    fun updateBook(book: ModelBook) {
        bookQueries.updateBook(
            title = book.title,
            author = book.author,
            description = book.description,
            coverUrl = book.coverUrl,
            progress = book.progress.toLong(),
            rating = book.rating.toLong(),
            review = book.review,
            genre = book.genre,
            id = book.id
        )
    }

    fun deleteBook(id: String) {
        bookQueries.deleteBook(id)
    }

    suspend fun getRecommendations(genre: String): Result<List<ModelBook>> {
        return try {
            val response = bookApiService.searchBooks("subject:$genre", "AIzaSyAPxaXgE24LDKgL63EVhKTIIDGZI5NG2gc")
            val books = response.items?.map { item ->
                ModelBook(
                    id = item.id,
                    title = item.volumeInfo.title,
                    author = item.volumeInfo.authors?.joinToString() ?: "Unknown",
                    description = item.volumeInfo.description ?: "No description",
                    coverUrl = item.volumeInfo.imageLinks?.thumbnail,
                    genre = genre
                )
            } ?: emptyList()

            books.forEach { book ->
                val existingBook = bookQueries.selectBookById(book.id).executeAsOneOrNull()
                if (existingBook == null) {
                    addBook(book)
                }
            }
            Result.Success(books)
        } catch (e: Exception) {
            Result.Error(e.message ?: "Failed to fetch recommendations")
        }
    }
}

fun toModelBook(book: Book) = ModelBook(
    id = book.id,
    title = book.title,
    author = book.author,
    description = book.description,
    coverUrl = book.coverUrl,
    progress = book.progress.toInt(),
    rating = book.rating.toInt(),
    review = book.review,
    genre = book.genre
)

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}