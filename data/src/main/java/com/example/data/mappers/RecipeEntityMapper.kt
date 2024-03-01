package com.example.data.mappers

import com.example.data.local.entity.IngredientEntity
import com.example.data.local.entity.InstructionEntity
import com.example.data.local.entity.NutrientEntity
import com.example.data.local.entity.RecipeEntity
import com.example.domain.models.Ingredient
import com.example.domain.models.Instruction
import com.example.domain.models.Nutrient
import com.example.domain.models.Recipe
import java.time.LocalDate
import java.util.Date

fun RecipeEntity.toDomain(
    instructionEntities: List<InstructionEntity>,
    ingredientEntities: List<IngredientEntity>,
    nutrientEntities: List<NutrientEntity>
): Recipe {
    return Recipe(
        id = this.baseId,
        title = this.title,
        sourceName = this.sourceName,
        sourceUrl = this.sourceUrl,
        imageUrl = this.imageUrl,
        readyInMinutes = this.readyInMinutes,
        servings = this.servings,
        summary = this.summary,
        score = this.score,
        instructions = instructionEntities.map { it.toDomain() },
        ingredients = ingredientEntities.map { it.toDomain() },
        nutrients = nutrientEntities.map { it.toDomain() }
    )
}

fun Recipe.toEntity():RecipeEntity{
    return RecipeEntity(
        0,
        baseId = this.id,
        title = this.title,
        sourceName = this.sourceName,
        sourceUrl = this.sourceUrl,
        imageUrl = this.imageUrl,
        readyInMinutes = this.readyInMinutes,
        servings = this.servings,
        summary = this.summary,
        score = this.score,
        createdAt = Date().time
    )
}

fun InstructionEntity.toDomain(): Instruction {
    return Instruction(this.number, this.step)
}

fun Instruction.toEntity(recipeId: Int): InstructionEntity {
    return InstructionEntity(0, recipeId, number, step)
}


fun IngredientEntity.toDomain(): Ingredient {
    return Ingredient(
        id, name, original, amount, unit
    )
}

fun Ingredient.toEntity(recipeId: Int): IngredientEntity {
    return IngredientEntity(0, recipeId, name, original, amount, unit)
}


fun NutrientEntity.toDomain(): Nutrient {
    return Nutrient(
        name, amount, unit
    )
}

fun Nutrient.toEntity(recipeId: Int): NutrientEntity {
    return NutrientEntity(
        0, recipeId, name, amount, unit
    )
}