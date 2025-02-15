package com.example.a3406.model

data class Book(
    val id: String, // 书籍的唯一 ID
    val title: String, // 书籍标题
    val author: String, // 书籍作者
    val description: String, // 书籍描述
    val coverUrl: String? = null // 书籍封面图片 URL（可选）
)