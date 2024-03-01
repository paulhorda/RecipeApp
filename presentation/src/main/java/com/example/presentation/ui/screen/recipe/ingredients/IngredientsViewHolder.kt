package com.example.presentation.ui.screen.recipe.ingredients

import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Ingredient
import com.example.recipeapp.databinding.ElementIngredientBinding
import java.util.Locale

class IngredientsViewHolder(private val binding: ElementIngredientBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(
        element: Ingredient,
        elementCallback: ((Ingredient) -> Unit?)?
    ) {
        binding.ingredientNameTv.text = element.name.replaceFirstChar(Char::titlecase)
        binding.ingredientOverviewTv.text = element.original
        binding.ingredientAmountTv.text = "${element.amount} ${element.unit}"

    }
}
