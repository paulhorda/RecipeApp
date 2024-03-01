package com.example.data.network.sevices

import com.example.data.network.models.RecipeNetwork
import com.example.data.network.models.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface RecipesApi {
    @GET("/recipes/complexSearch?fillIngredients=true&addRecipeNutrition=true")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("number") number:  Int,
        @Query("offset") offset: Int
    ): RecipeResponse

    @GET("/recipes/complexSearch?fillIngredients=true")
    suspend fun searchRecipes(
        @Query("addRecipeInformation") addRecipeInformation: Boolean,
        @Query("addRecipeNutrition") addRecipeNutrition: Boolean,
        @Query("number") number:  Int,
        @Query("offset") offset: Int,
        @QueryMap options: Map<String, String>
    ): RecipeResponse

    @GET("/recipes/{id}/information?includeNutrition=true")
    suspend fun requestRecipeInformation(
        @Path("id") id: Int
    ): RecipeNetwork
}