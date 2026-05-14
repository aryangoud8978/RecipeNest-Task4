package com.example.recipenest.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

data class MealResponse(
    val meals: List<Meal>
)

data class Meal(

    val idMeal: String,

    val strMeal: String,

    val strMealThumb: String,

    val strCategory: String,

    val strInstructions: String
)

interface RecipeApi {

    @GET("search.php?s=")
    suspend fun getRecipes(): MealResponse
}

object RetrofitInstance {

    val api: RecipeApi by lazy {

        Retrofit.Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
            .create(RecipeApi::class.java)
    }
}