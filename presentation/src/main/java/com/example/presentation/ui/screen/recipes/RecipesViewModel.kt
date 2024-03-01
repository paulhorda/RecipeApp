package com.example.presentation.ui.screen.recipes

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Recipe
import com.example.domain.usecases.DeleteFavoriteRecipeUseCase
import com.example.domain.usecases.GetFavoriteRecipesListUseCase
import com.example.domain.usecases.GetRecipesUseCase
import com.example.domain.usecases.SaveFavoriteRecipeUseCase
import com.example.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val saveFavoriteRecipeUseCase: SaveFavoriteRecipeUseCase,
    private val deleteFavoriteRecipeUseCase: DeleteFavoriteRecipeUseCase,
    private val getFavoriteRecipesListUseCase: GetFavoriteRecipesListUseCase
) : ViewModel() {

    private val _liveData: MutableLiveData<Resource<List<Recipe>>> = MutableLiveData()
    val liveData: MutableLiveData<Resource<List<Recipe>>> get() = _liveData

    val recipesAdapter = RecipesAdapter()

    var offset = 0

    fun getRecipes(searchString: String = "") {
        _liveData.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val data =
                    if (searchString.isBlank()) getRecipesUseCase.invoke(10, offset, emptyMap())
                    else getRecipesUseCase.invoke(query = searchString, 10, offset)

                val favorites = getFavoriteRecipesListUseCase()

                data.forEachIndexed { index, recipe ->
                    favorites.forEach { favorite ->
                        if (favorite.id == recipe.id) {
                            data[index].isLike = true
                        }
                    }
                }
                _liveData.value = Resource.Success(data)
            } catch (e: Exception) {
                _liveData.value = Resource.Error("Failed to load data \n${e.message}", null)
            }
        }
    }

    fun setRecipeFavorite(recipe: Recipe) {
        viewModelScope.launch {
            saveFavoriteRecipeUseCase(recipe)
        }
    }

    fun deleteFavoriteRecipe(recipe: Recipe) {
        viewModelScope.launch {
            deleteFavoriteRecipeUseCase(recipe)
        }
    }
}