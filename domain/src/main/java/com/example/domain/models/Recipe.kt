package com.example.domain.models

data class Recipe(
    var id: Int,
    val title: String,
    val sourceName: String?,
    val sourceUrl: String?,
    val imageUrl: String,
    val readyInMinutes: Int?,
    val servings: Int?,
    val summary: String?,
    val score: Float?,
    val instructions: List<Instruction>?,
    val ingredients: List<Ingredient>?,
    val nutrients: List<Nutrient>?,
    var isLike: Boolean = false
)

