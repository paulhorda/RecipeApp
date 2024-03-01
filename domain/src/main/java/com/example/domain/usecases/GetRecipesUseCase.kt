package com.example.domain.usecases

import com.example.domain.models.Recipe
import com.example.domain.repository.RecipesRepository
import javax.inject.Inject

class GetRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {
     suspend operator fun invoke(count: Int, offset: Int, options: Map<String, String>): List<Recipe> {
        return recipesRepository.searchRecipes(
            addRecipeInformation = true,
            addRecipeNutrition = true,
            number = count,
            offset = offset,
            options = options
        )
    }

    suspend operator fun invoke(query: String, count: Int, offset: Int): List<Recipe> {
        return recipesRepository.searchRecipes(
            query,
            addRecipeInformation = true,
            number = count,
            offset = offset,
        )
    }
}