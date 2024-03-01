package com.example.presentation.ui.screen.recipe.instructions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Ingredient
import com.example.domain.models.Instruction
import com.example.presentation.ui.screen.recipes.DiffUtilCallback
import com.example.recipeapp.databinding.ElementIngredientBinding

class InstructionsAdapter : RecyclerView.Adapter<InstructionsViewHolder>() {

    var data: List<Instruction> = emptyList()
        set(value) {
            val diffCallback = DiffUtilCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    var elementCallback: ((Ingredient) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InstructionsViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = ElementIngredientBinding.inflate(inflate, parent, false)
        return InstructionsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: InstructionsViewHolder, position: Int) {
        holder.bind(data[position], elementCallback)
    }

    override fun getItemCount(): Int = data.size

}
