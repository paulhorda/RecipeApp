package com.example.presentation.ui.screen.recipe.instructions

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Ingredient
import com.example.domain.models.Instruction
import com.example.recipeapp.databinding.ElementIngredientBinding

class InstructionsViewHolder(private val binding: ElementIngredientBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        element: Instruction,
        elementCallback: ((Ingredient) -> Unit?)?
    ) {
        binding.ingredientNameTv.text = "Step ${element.number}"
        binding.ingredientOverviewTv.text = element.step
    }
}
