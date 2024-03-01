package com.example.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Ingredients",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipe_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class IngredientEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "recipe_id") val recipeId: Int,
    val name: String,
    val original: String,
    val amount: Float,
    val unit: String
)