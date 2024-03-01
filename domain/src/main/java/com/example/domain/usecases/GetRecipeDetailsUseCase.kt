package com.example.domain.usecases

import com.example.domain.models.Recipe
import com.example.domain.repository.RecipesRepository
import javax.inject.Inject

class GetRecipeDetailsUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {
    suspend operator fun invoke(recipeId: Int): Recipe {
        return recipesRepository.requestRecipeInformation(recipeId)
    }
}