package com.example.data.mappers

import com.example.data.local.entity.IngredientEntity
import com.example.data.local.entity.InstructionEntity
import com.example.data.local.entity.NutrientEntity
import com.example.data.local.entity.RecipeEntity
import com.example.data.network.models.RecipeNetwork
import com.example.domain.models.Ingredient
import com.example.domain.models.Instruction
import com.example.domain.models.Nutrient
import com.example.domain.models.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

fun List<RecipeNetwork>.toDomain(): List<Recipe> {
    return this.map { recipe ->
        recipe.toDomain()
    }
}

fun RecipeNetwork.toDomain(): Recipe {
    return Recipe(
        id = this.id,
        title = this.title,
        sourceName = this.sourceName,
        sourceUrl = this.sourceUrl,
        imageUrl = this.imageUrl,
        readyInMinutes = this.readyInMinutes,
        servings = this.servings,
        summary = this.summary,
        score = this.score,
        instructions = (this.instructions?.firstOrNull()?.steps?.toDomain() ?: emptyList()),
        ingredients = this.ingredients?.toDomain() ?: emptyList(),
        nutrients = this.nutrition?.nutrients?.toDomain() ?: emptyList()
    )
}

