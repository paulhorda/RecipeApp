package com.example.domain.usecases

import com.example.domain.models.Recipe
import com.example.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteRecipeUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {
    operator fun invoke(recipeId: Int): Flow<Recipe> {
        return recipesRepository.requestFavoriteRecipeById(recipeId)
    }
}