package com.example.domain.usecases

import com.example.domain.models.Recipe
import com.example.domain.repository.RecipesRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetFavoriteRecipesListUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
){
    suspend operator fun invoke(): List<Recipe> {
        return recipesRepository.requestFavoriteRecipes().first()
    }
}