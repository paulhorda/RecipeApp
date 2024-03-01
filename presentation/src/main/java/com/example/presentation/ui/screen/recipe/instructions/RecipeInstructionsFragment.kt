package com.example.presentation.ui.screen.recipe.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.domain.models.Instruction
import com.example.presentation.ui.screen.recipe.INSTRUCTION_KEY
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentRecipeInstructionsBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RecipeInstructionsFragment: Fragment() {
    private lateinit var binding: FragmentRecipeInstructionsBinding
    private val viewModel: RecipeInstructionsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeInstructionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val instructionsJson = arguments?.getString(INSTRUCTION_KEY, "")
        val listType = object : TypeToken<List<Instruction>>() {}

        val instructions = Gson().fromJson(instructionsJson, listType)

        binding.instructionRv.adapter = viewModel.adapter

        binding.instructionCountTv.text = getString(R.string.steps, instructions.size.toString())
        viewModel.adapter.data = instructions


    }

    fun getFragmentHeight(): Int {
        return binding.root.height
    }
}