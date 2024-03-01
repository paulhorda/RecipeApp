package com.example.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Instructions",
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = ["id"],
            childColumns = ["recipe_id"],
            onDelete = ForeignKey.CASCADE
        )
    ])
data class InstructionEntity (
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "recipe_id") val recipeId: Int,
    val number: Int,
    val step: String
)