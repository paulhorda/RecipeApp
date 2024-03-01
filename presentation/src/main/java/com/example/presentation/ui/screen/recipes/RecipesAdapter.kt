package com.example.presentation.ui.screen.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.models.Recipe
import com.example.recipeapp.databinding.RecipeElementBinding

class RecipesAdapter : RecyclerView.Adapter<RecipesViewHolder>() {

    var data: List<Recipe> = emptyList()
        set(value) {
            val diffCallback = DiffUtilCallback(field, value)
            val diffResult = DiffUtil.calculateDiff(diffCallback)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    var elementCallback: ((Recipe) -> Unit?)? = null

    var likeCallback: ((Recipe) -> Unit?)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val binding = RecipeElementBinding.inflate(inflate, parent, false)
        return RecipesViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        holder.bind(data[position], elementCallback, likeCallback)
    }
}