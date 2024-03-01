package com.example.presentation.ui.screen.recipe.instructions

import androidx.lifecycle.ViewModel
import com.example.presentation.ui.screen.recipe.instructions.InstructionsAdapter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecipeInstructionsViewModel @Inject constructor(

): ViewModel() {
    val adapter = InstructionsAdapter()
}