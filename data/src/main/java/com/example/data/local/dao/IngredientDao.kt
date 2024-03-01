package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.local.entity.IngredientEntity

@Dao
interface IngredientDao {
    @Insert
    suspend fun insertIngredients(ingredients: List<IngredientEntity>)

    @Query("SELECT * FROM Ingredients WHERE recipe_id=:recipeId")
    suspend fun getIngredientsByRecipeId(recipeId: Int): List<IngredientEntity>


    @Query("DELETE FROM Ingredients WHERE recipe_id = :recipeId")
    suspend fun deleteIngredientsByRecipeId(recipeId: Int)
}