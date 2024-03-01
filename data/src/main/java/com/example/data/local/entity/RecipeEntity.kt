package com.example.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey

@Entity(tableName = "Recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "base_id") val baseId: Int,
    val title: String,
    @ColumnInfo(name = "source_name") val sourceName: String?,
    @ColumnInfo(name = "source_url") val sourceUrl: String?,
    @ColumnInfo(name = "ready_in_minutes") val readyInMinutes: Int?,
    val servings: Int?,
    @ColumnInfo(name = "image_url") var imageUrl: String,
    val summary: String?,
    val score: Float?,
    @ColumnInfo(name = "created_at") val createdAt: Long,
)