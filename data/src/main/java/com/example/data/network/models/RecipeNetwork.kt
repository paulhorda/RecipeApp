package com.example.data.network.models

import com.google.gson.annotations.SerializedName

data class RecipeNetwork(
    val id: Int,
    val sourceName: String?,
    val title: String,
    val readyInMinutes: Int?,
    val servings: Int?,
    val sourceUrl: String?,
    @SerializedName("image")
    val imageUrl: String,
    val summary: String?,
    @SerializedName("spoonacularScore")
    val score: Float?,
    @SerializedName("analyzedInstructions")
    val instructions: List<InstructionNetwork>?,
    @SerializedName("extendedIngredients")
    val ingredients: List<IngredientNetwork>?,
    val nutrition: NutritionNetwork?
)