package com.example.presentation.ui.screen.recipe.ingredients

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeIngredientsViewModel @Inject constructor(

) : ViewModel() {
    val adapter = IngredientsAdapter()
}