package com.example.presentation.ui.screen.favorites

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Recipe
import com.example.domain.usecases.DeleteFavoriteRecipeUseCase
import com.example.domain.usecases.GetFavoriteRecipesFlowUseCase
import com.example.domain.usecases.SaveFavoriteRecipeUseCase
import com.example.presentation.ui.screen.recipes.RecipesAdapter
import com.example.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteRecipesFlowUseCase: GetFavoriteRecipesFlowUseCase,
    private val deleteFavoriteRecipeUseCase: DeleteFavoriteRecipeUseCase,
    private val saveFavoriteRecipeUseCase: SaveFavoriteRecipeUseCase
) : ViewModel() {

    private val _liveData: MutableLiveData<Resource<List<Recipe>>> = MutableLiveData()
    val liveData: MutableLiveData<Resource<List<Recipe>>> get() = _liveData

    val adapter = RecipesAdapter()

    fun observeFavorites() {
        _liveData.value = Resource.Loading()
        viewModelScope.launch {
            
            try {
                getFavoriteRecipesFlowUseCase().collect { recipes ->
                    recipes.forEach { it.isLike = true }
                    if (recipes.isNotEmpty())
                        _liveData.value = Resource.Success(recipes)
                    else
                        _liveData.value = Resource.Error("Favorites is not added yet...", null)
                }
            } catch (e: Exception) {
                _liveData.value = Resource.Error("Failed to load data \n${e.message}", null)
            }
        }
    }

    fun deleteFavoriteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            deleteFavoriteRecipeUseCase(recipe)
        }
    }

    fun saveRecipeFavorite(recipe: Recipe) {
        viewModelScope.launch {
            saveFavoriteRecipeUseCase(recipe)
        }
    }
}