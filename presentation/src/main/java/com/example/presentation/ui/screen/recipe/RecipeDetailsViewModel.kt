package com.example.presentation.ui.screen.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.Recipe
import com.example.domain.usecases.GetFavoriteRecipeUseCase
import com.example.domain.usecases.GetRecipeDetailsUseCase
import com.example.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeDetailsViewModel @Inject constructor(
    private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase,
    private val getFavoriteRecipeUseCase: GetFavoriteRecipeUseCase
) : ViewModel() {

    private val _liveData: MutableLiveData<Resource<Recipe>> = MutableLiveData()
    val liveData: MutableLiveData<Resource<Recipe>> get() = _liveData

    fun getRecipe(recipeId: Int, isLocal: Boolean) {
        _liveData.value =  Resource.Loading()
        viewModelScope.launch {
            try {
                if (isLocal)
                    getFavoriteRecipeUseCase.invoke(recipeId).collect{
                        _liveData.value = Resource.Success(it)
                    }
                else
                    _liveData.value = Resource.Success(getRecipeDetailsUseCase.invoke(recipeId))
            }catch (e: Exception){
                _liveData.value = Resource.Error("Something went wrong...", null)
            }

        }
    }
}