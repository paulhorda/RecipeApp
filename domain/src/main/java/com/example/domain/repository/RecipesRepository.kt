package com.example.domain.repository

import com.example.domain.models.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    suspend fun searchRecipes(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<Recipe>

    suspend fun searchRecipes(
        addRecipeInformation: Boolean,
        addRecipeNutrition: Boolean,
        number: Int,
        offset: Int,
        options: Map<String, String>
    ): List<Recipe>

    suspend fun requestRecipeInformation(id: Int): Recipe

    fun requestFavoriteRecipes(): Flow<List<Recipe>>

    suspend fun saveFavoriteRecipe(recipe: Recipe): Int

    fun requestFavoriteRecipeById(id: Int): Flow<Recipe>

    suspend fun deleteFavoriteRecipe(recipe: Recipe)
}