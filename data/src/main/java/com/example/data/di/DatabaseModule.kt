package com.example.data.di

import android.content.Context
import androidx.room.Room
import com.example.data.local.AppDatabase
import com.example.data.local.dao.IngredientDao
import com.example.data.local.dao.InstructionDao
import com.example.data.local.dao.NutrientDao
import com.example.data.local.dao.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {
    @Provides
    fun provideContext(@ApplicationContext context: Context): Context {
        return context
    }

    @Provides
    fun providesRecipeDao(appDatabase: AppDatabase): RecipeDao =
        appDatabase.recipeDao()

    @Provides
    fun providesInstructionDao(appDatabase: AppDatabase): InstructionDao =
        appDatabase.instructionDao()

    @Provides
    fun providesIngredientDao(appDatabase: AppDatabase): IngredientDao =
        appDatabase.ingredientDao()

    @Provides
    fun providesNutrientDao(appDatabase: AppDatabase): NutrientDao =
        appDatabase.nutrientDao()

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "RecipeApp"
        ).build()
    }
}