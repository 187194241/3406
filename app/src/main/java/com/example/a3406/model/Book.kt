package com.example.a3406.model

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val coverUrl: String? = null,
    val progress: Int = 0,
    val rating: Int = 0,
    val review: String? = null,
    val genre: String? = null
)