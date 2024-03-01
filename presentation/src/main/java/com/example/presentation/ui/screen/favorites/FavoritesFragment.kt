package com.example.presentation.ui.screen.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.presentation.ui.screen.recipes.RecipesFragmentDirections
import com.example.presentation.utils.Resource
import com.example.recipeapp.databinding.FragmentFavoritesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private val viewModel: FavoritesViewModel by viewModels()

    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configRecipesAdapter()

        configRecipesAdapterCallbacks()
    }

    private fun configRecipesAdapter(){
        binding.recipesRv.adapter = viewModel.adapter

        viewModel.liveData.observe(viewLifecycleOwner) {
            when(it){
                is Resource.Loading -> {
                    binding.spinner.visibility = View.VISIBLE
                    binding.recipesRv.visibility = View.GONE
                    binding.emptyResultContainerLl.visibility = View.GONE
                }
                is Resource.Success -> {
                    binding.spinner.visibility = View.GONE
                    binding.emptyResultContainerLl.visibility = View.GONE
                    binding.recipesRv.visibility = View.VISIBLE

                    viewModel.adapter.data = it.data?: emptyList()
                }
                is Resource.Error -> {
                    binding.spinner.visibility = View.GONE
                    binding.emptyResultContainerLl.visibility = View.VISIBLE
                    binding.recipesRv.visibility = View.GONE

                    binding.infoTv.text = it.message
                }
            }
        }

        viewModel.observeFavorites()
    }

    private fun configRecipesAdapterCallbacks(){
        viewModel.adapter.likeCallback = {
            val likeState = it.isLike
            it.isLike = !it.isLike
            if (likeState)
                viewModel.deleteFavoriteRecipe(it)
            else
                viewModel.saveRecipeFavorite(it)
        }

        viewModel.adapter.elementCallback = {
            val action =
                FavoritesFragmentDirections.actionFavoritesFragmentToRecipeDetailsFragment(it.id, true)
            view?.findNavController()?.navigate(action)
        }
    }
}