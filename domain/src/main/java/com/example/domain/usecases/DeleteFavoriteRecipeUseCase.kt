package com.example.domain.usecases

import com.example.domain.models.Recipe
import com.example.domain.repository.RecipesRepository
import javax.inject.Inject

class DeleteFavoriteRecipeUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {
    suspend operator fun invoke(recipe: Recipe){
        recipesRepository.deleteFavoriteRecipe(recipe)
    }
}