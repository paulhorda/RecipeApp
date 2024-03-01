package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.data.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Query("SELECT * FROM Recipes")
    fun getRecipes(): Flow<List<RecipeEntity>>

    @Insert
    suspend fun insertRecipe(recipe: RecipeEntity): Long

    @Query("SELECT * FROM Recipes WHERE base_id=:recipeId")
    fun getRecipe(recipeId: Int): Flow<RecipeEntity>

    @Query("SELECT * FROM Recipes WHERE base_id=:baseId")
    suspend fun getRecipeByBaseId(baseId: Int): RecipeEntity

    @Delete
    suspend fun deleteRecipe(recipe: RecipeEntity)


}