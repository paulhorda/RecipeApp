package com.example.presentation.ui.screen.recipes

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.presentation.utils.Resource
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentRecipesBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private val viewModel: RecipesViewModel by viewModels()

    private lateinit var binding: FragmentRecipesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecipesAdapter()
        configSwipeToRefresh()
        configRecipesAdapterCallbacks()
        configSearchRecipes()

    }

    private fun configSearchRecipes() {
        binding.searchTiet.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val newText = s.toString()
                if (newText.count() >= 3)
                    viewModel.getRecipes(newText)
            }
        })
    }

    private fun configRecipesAdapter() {
        binding.recipesRv.adapter = viewModel.recipesAdapter

        viewModel.liveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.spinner.visibility = View.VISIBLE
                    binding.recipesRv.visibility = View.GONE
                    binding.emptyResultContainerLl.visibility = View.GONE
                }

                is Resource.Success -> {
                    binding.spinner.visibility = View.GONE
                    binding.emptyResultContainerLl.visibility = View.GONE
                    binding.recipesRv.visibility = View.VISIBLE

                    viewModel.recipesAdapter.data = it.data ?: emptyList()
                    if (it.data?.isEmpty() == true)
                        configOnEmptyList()
                }

                is Resource.Error -> {
                    binding.spinner.visibility = View.GONE
                    binding.emptyResultContainerLl.visibility = View.VISIBLE
                    binding.recipesRv.visibility = View.GONE

                    binding.infoTv.text = it.message
                }
            }

        }
        viewModel.getRecipes()
    }

    private fun configOnEmptyList() {
        binding.recipesRv.visibility = View.GONE
        binding.emptyResultContainerLl.visibility = View.VISIBLE
        binding.infoTv.text = getString(R.string.no_recipes)
    }

    private fun configSwipeToRefresh() {
        binding.swipeSrl.setOnRefreshListener {
            viewModel.offset += 10
            viewModel.getRecipes()
            binding.swipeSrl.isRefreshing = false
        }
    }

    private fun configRecipesAdapterCallbacks() {
        viewModel.recipesAdapter.likeCallback = {
            val likeState = it.isLike
            it.isLike = !it.isLike
            if (likeState)
                viewModel.deleteFavoriteRecipe(it)
            else
                viewModel.setRecipeFavorite(it)
        }

        viewModel.recipesAdapter.elementCallback = {
            val action =
                RecipesFragmentDirections.actionRecipesFragmentToRecipeDetailsFragment(it.id)
            view?.findNavController()?.navigate(action)
        }
    }
}