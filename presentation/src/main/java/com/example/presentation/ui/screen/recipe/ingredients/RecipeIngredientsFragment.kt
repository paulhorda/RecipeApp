package com.example.presentation.ui.screen.recipe.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.domain.models.Ingredient
import com.example.presentation.ui.screen.recipe.INGREDIENT_KEY
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentRecipeIngredientsBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeIngredientsFragment: Fragment() {

    lateinit var binding: FragmentRecipeIngredientsBinding
    private val viewModel: RecipeIngredientsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeIngredientsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val ingredientsJson = arguments?.getString(INGREDIENT_KEY, "")
        val listType = object : TypeToken<List<Ingredient>>() {}

        val ingredients = Gson().fromJson(ingredientsJson, listType)

        binding.ingredientsRv.adapter = viewModel.adapter

        binding.ingredientsCountTv.text = getString(R.string.items, ingredients.size.toString())
        viewModel.adapter.data = ingredients
    }

    fun getFragmentHeight(): Int {
        return binding.root.height
    }
}