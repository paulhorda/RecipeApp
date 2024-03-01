package com.example.presentation.ui.screen.recipe

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.domain.models.Ingredient
import com.example.domain.models.Instruction
import com.example.presentation.ui.screen.recipe.ingredients.RecipeIngredientsFragment
import com.example.presentation.ui.screen.recipe.instructions.RecipeInstructionsFragment
import com.google.gson.Gson


const val INSTRUCTION_KEY = "instructions"
const val INGREDIENT_KEY = "ingredients"

class ViewPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    var instructions: List<Instruction> = emptyList()
    var ingredients: List<Ingredient> = emptyList()

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val gson = Gson()
        val bundle = Bundle()

        when (position) {
            0 -> {
                val fragment = RecipeIngredientsFragment()

                bundle.putString(INGREDIENT_KEY, gson.toJson(ingredients))
                fragment.arguments = bundle

                return fragment
            }
            1 -> {
                val fragment = RecipeInstructionsFragment()

                bundle.putString(INSTRUCTION_KEY, gson.toJson(instructions))
                fragment.arguments = bundle

                return fragment
            }
            else -> {
                val fragment = RecipeIngredientsFragment()

                bundle.putString(INGREDIENT_KEY, gson.toJson(ingredients))
                fragment.arguments = bundle

                return fragment
            }
        }
    }

}