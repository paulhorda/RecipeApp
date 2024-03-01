package com.example.data.local

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.IngredientDao
import com.example.data.local.dao.InstructionDao
import com.example.data.local.dao.NutrientDao
import com.example.data.local.dao.RecipeDao
import com.example.data.local.entity.IngredientEntity
import com.example.data.local.entity.InstructionEntity
import com.example.data.local.entity.NutrientEntity
import com.example.data.local.entity.RecipeEntity

@Keep
@Database(entities = [RecipeEntity::class, InstructionEntity::class, IngredientEntity::class, NutrientEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    abstract fun instructionDao(): InstructionDao
    abstract fun ingredientDao(): IngredientDao
    abstract fun nutrientDao(): NutrientDao
}