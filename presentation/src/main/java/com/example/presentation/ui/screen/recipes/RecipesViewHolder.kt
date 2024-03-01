package com.example.presentation.ui.screen.recipes

import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.domain.models.Recipe
import com.example.recipeapp.R
import com.example.recipeapp.databinding.RecipeElementBinding

class RecipesViewHolder(private val binding: RecipeElementBinding) : ViewHolder(binding.root) {
    fun bind(
        element: Recipe,
        elementCallback: ((Recipe) -> Unit?)?,
        likeCallback: ((Recipe) -> Unit?)?,
    ) {
        binding.recipeNameTv.text = element.title
        binding.authorNameTv.text = element.sourceName

        val elementImage  = element.imageUrl.toUri()

        Glide.with(binding.recipeIv)
            .load(elementImage)
            .centerCrop()
            .placeholder(setCircularProgressDrawable())
            .into(binding.recipeIv)

        setLikeImageResource(element)

        binding.likeIv.setOnClickListener {
            likeCallback?.invoke(element)
            setLikeImageResource(element)
        }
        binding.root.setOnClickListener { elementCallback?.invoke(element) }
    }

    private fun setCircularProgressDrawable(): CircularProgressDrawable {
        val circularProgressDrawable = CircularProgressDrawable(binding.recipeIv.context)

        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        return circularProgressDrawable
    }

    private fun setLikeImageResource(element: Recipe) {
        binding.likeIv.setImageResource(
            if (element.isLike)
                R.drawable.heart_icon__1_
            else R.drawable.heart_icon_48
        )

    }
}
