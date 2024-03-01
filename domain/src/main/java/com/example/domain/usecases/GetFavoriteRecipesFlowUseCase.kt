package com.example.domain.usecases

import com.example.domain.models.Recipe
import com.example.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteRecipesFlowUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) {
    operator fun invoke(): Flow<List<Recipe>>{
        return recipesRepository.requestFavoriteRecipes()
    }
}