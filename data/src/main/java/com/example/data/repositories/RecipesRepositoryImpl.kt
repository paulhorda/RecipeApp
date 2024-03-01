package com.example.data.repositories

import android.content.Context
import com.example.data.local.dao.IngredientDao
import com.example.data.local.dao.InstructionDao
import com.example.data.local.dao.NutrientDao
import com.example.data.local.dao.RecipeDao
import com.example.data.mappers.toDomain
import com.example.data.mappers.toEntity
import com.example.data.network.sevices.RecipesApi
import com.example.domain.models.Recipe
import com.example.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class RecipesRepositoryImpl @Inject constructor(
    private val service: RecipesApi,
    private val recipeDao: RecipeDao,
    private val instructionDao: InstructionDao,
    private val ingredientDao: IngredientDao,
    private val nutrientDao: NutrientDao
) : RecipesRepository {
    override suspend fun searchRecipes(
        query: String,
        addRecipeInformation: Boolean,
        number: Int,
        offset: Int
    ): List<Recipe> {
        return service.searchRecipes(query, addRecipeInformation, number, offset).results.toDomain()
    }

    override suspend fun searchRecipes(
        addRecipeInformation: Boolean,
        addRecipeNutrition: Boolean,
        number: Int,
        offset: Int,
        options: Map<String, String>
    ): List<Recipe> {
        return service.searchRecipes(
            addRecipeInformation,
            addRecipeNutrition,
            number,
            offset,
            options
        ).results.toDomain()
    }

    override suspend fun requestRecipeInformation(id: Int): Recipe {
        return service.requestRecipeInformation(id).toDomain()
    }

    override fun requestFavoriteRecipes(): Flow<List<Recipe>> {
        return recipeDao.getRecipes().map { recipeDao ->
            recipeDao.map { recipeEntity ->
                recipeEntity.toDomain(
                    instructionDao.getInstructionsByRecipeId(recipeEntity.id),
                    ingredientDao.getIngredientsByRecipeId(recipeEntity.id),
                    nutrientDao.getNutrientsByRecipeId(recipeEntity.id)
                )
            }
        }
    }

    override suspend fun saveFavoriteRecipe(recipe: Recipe): Int {
        val recipeEntity = recipe.toEntity()

        recipeDao.insertRecipe(recipeEntity).let { recipeId ->
            val recipeIngredients = recipe.ingredients?.map { it.toEntity(recipeId.toInt()) }
            val recipeInstructions = recipe.instructions?.map { it.toEntity(recipeId.toInt()) }
            val recipeNutrients = recipe.nutrients?.map { it.toEntity(recipeId.toInt()) }

            ingredientDao.insertIngredients(recipeIngredients ?: emptyList())
            instructionDao.insertInstructions(recipeInstructions ?: emptyList())
            nutrientDao.insertNutrients(recipeNutrients ?: emptyList())

            return recipeId.toInt()
        }
    }

    override fun requestFavoriteRecipeById(id: Int): Flow<Recipe> {
        val recipe = recipeDao.getRecipe(id)
        return recipe.map {
            it.toDomain(
                instructionDao.getInstructionsByRecipeId(it.id),
                ingredientDao.getIngredientsByRecipeId(it.id),
                nutrientDao.getNutrientsByRecipeId(it.id)
            )
        }
    }

    override suspend fun deleteFavoriteRecipe(recipe: Recipe) {
        val recipeEntity = recipeDao.getRecipeByBaseId(recipe.id)
        recipeDao.deleteRecipe(recipeEntity)
    }
}

