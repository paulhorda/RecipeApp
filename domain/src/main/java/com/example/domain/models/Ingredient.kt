package com.example.domain.models

data class Ingredient(
    val id: Int,
    val name: String,
    val original: String,
    val amount: Float,
    val unit: String
)
