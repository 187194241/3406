package com.example.a3406.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val coverUrl: String? = null,
    val progress: Int = 0, // Percentage (0-100)
    val rating: Int = 0, // 1-5 stars
    val review: String? = null,
    val genre: String? = null // For personalized recommendations
)