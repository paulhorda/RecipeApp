package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.local.entity.NutrientEntity

@Dao
interface NutrientDao {

    @Insert
    suspend fun insertNutrients(nutrients: List<NutrientEntity>)

    @Query("SELECT * FROM Nutrients WHERE recipe_id=:recipeId")
    suspend fun getNutrientsByRecipeId(recipeId: Int): List<NutrientEntity>

    @Query("DELETE FROM Nutrients WHERE recipe_id = :recipeId")
    suspend fun deleteNutrientsByRecipeId(recipeId: Int)
}