package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.data.local.entity.InstructionEntity

@Dao
interface InstructionDao {
    @Insert
    suspend fun insertInstructions(instructions: List<InstructionEntity>)

    @Query("SELECT * FROM Instructions WHERE recipe_id=:recipeId")
    suspend fun getInstructionsByRecipeId(recipeId: Int): List<InstructionEntity>

    @Query("DELETE FROM Instructions WHERE recipe_id = :recipeId")
    suspend fun deleteInstructionsByRecipeId(recipeId: Int)
}