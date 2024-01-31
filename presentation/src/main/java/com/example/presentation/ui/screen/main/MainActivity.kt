package com.example.presentation.ui.screen.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recipeapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navHostFragment
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }

    private fun configureActionBarBackButtonVisibility() {
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.recipesFragment,
                R.id.favoritesFragment ->
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)

                else ->
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
            title = destination.label
        }
    }
}