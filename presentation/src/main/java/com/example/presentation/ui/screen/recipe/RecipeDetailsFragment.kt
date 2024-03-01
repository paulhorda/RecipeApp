package com.example.presentation.ui.screen.recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.domain.models.Recipe
import com.example.presentation.utils.Resource
import com.example.recipeapp.R
import com.example.recipeapp.databinding.FragmentRecipeDetailsBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeDetailsFragment : Fragment() {

    private val viewModel: RecipeDetailsViewModel by viewModels()
    private val args: RecipeDetailsFragmentArgs by navArgs()
    private lateinit var binding: FragmentRecipeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecipeDetailsBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getRecipe(args.recipeId, args.isLocal)


        viewModel.liveData.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    binding.spinner.visibility = View.VISIBLE
                    binding.content.visibility = View.GONE
                    binding.emptyResultContainerLl.visibility = View.GONE
                }

                is Resource.Success -> {
                    binding.spinner.visibility = View.GONE
                    binding.emptyResultContainerLl.visibility = View.GONE
                    binding.content.visibility = View.VISIBLE

                    val recipe = it.data ?: Recipe(
                        0,
                        "",
                        "",
                        "",
                        "",
                        0,
                        0,
                        "",
                        0.0f,
                        emptyList(),
                        emptyList(),
                        emptyList()
                    )

                    configViewPagerAdapter(
                        recipe
                    )
                    setTabMediator()
                    configOutputRecipeInfo(recipe)
                }

                is Resource.Error -> {
                    binding.spinner.visibility = View.GONE
                    binding.emptyResultContainerLl.visibility = View.VISIBLE
                    binding.content.visibility = View.GONE

                    binding.infoTv.text = it.message
                }
            }

        }


    }

    private fun configViewPagerAdapter(it: Recipe) {
        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.instructions = it.instructions ?: emptyList()
        viewPagerAdapter.ingredients = it.ingredients ?: emptyList()

        binding.viewPager.adapter = viewPagerAdapter
    }

    private fun configOutputRecipeInfo(it: Recipe) {
        with(binding) {
            recipeTitleTv.text = it.title
            recipeContentTv.text =
                HtmlCompat.fromHtml(it.summary.toString(), FROM_HTML_MODE_LEGACY).toString()
            timeTv.text = getString(R.string.ready_in_min, it.readyInMinutes.toString())

            Glide.with(recipePhotoIv)
                .load(it.imageUrl)
                .centerCrop()
                .placeholder(setCircularProgressDrawable())
                .into(recipePhotoIv)
        }
    }

    private fun setTabMediator() {
        TabLayoutMediator(binding.tabsTl, binding.viewPager) { tab, position ->
            when (position) {
                1 -> tab.text = getString(R.string.instructions)
                0 -> tab.text = getString(R.string.ingredients)
            }
        }.attach()
    }

    private fun setCircularProgressDrawable(): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(binding.recipePhotoIv.context)

        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        return circularProgressDrawable
    }
}