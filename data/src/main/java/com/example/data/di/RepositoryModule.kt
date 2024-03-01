package com.example.data.di

import android.content.Context
import com.example.data.repositories.RecipesRepositoryImpl
import com.example.domain.repository.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun provideRecipesRepository(recipesRepositoryImpl: RecipesRepositoryImpl):RecipesRepository
}