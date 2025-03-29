package com.example.a3406.network

import com.squareup.moshi.Json

data class BookResponse(
    val items: List<BookItem>?
)

data class BookItem(
    val id: String,
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val title: String,
    val authors: List<String>?,
    val description: String?,
    @Json(name = "imageLinks") val imageLinks: ImageLinks?
)

data class ImageLinks(
    val thumbnail: String?
)