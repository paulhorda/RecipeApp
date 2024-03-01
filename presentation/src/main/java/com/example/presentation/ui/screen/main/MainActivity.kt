package com.example.presentation.ui.screen.main

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.recipeapp.R
import com.example.recipeapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val navHostFragment
        get() = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        setSupportActionBar(binding.materialToolbar)
        binding.bottomNavigationView.setupWithNavController(navHostFragment.navController)

        configureActionBarBackButtonVisibility()
        setupToolbarNavigationBackButton()
    }

    private fun configureActionBarBackButtonVisibility() {
        navHostFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.recipesFragment,
                R.id.favoritesFragment -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(false)
                    binding.bottomNavigationView.visibility = View.VISIBLE
                }

                else -> {
                    supportActionBar?.setDisplayHomeAsUpEnabled(true)
                    binding.bottomNavigationView.visibility = View.GONE

                }            }
            title = destination.label
        }
    }

    private fun setupToolbarNavigationBackButton() {
        binding.materialToolbar.setNavigationOnClickListener {
            navHostFragment.navController.popBackStack()
        }
    }
}