package com.example.recipenest.utils

import androidx.compose.runtime.mutableStateListOf
import com.example.recipenest.model.RecentRecipe

object RecentRecipeManager {

    val recentRecipes =
        mutableStateListOf<RecentRecipe>()

    fun addRecipe(
        recipe: RecentRecipe
    ) {

        recentRecipes.removeAll {
            it.id == recipe.id
        }

        recentRecipes.add(
            index = 0,
            element = recipe
        )

        if (recentRecipes.size > 10) {

            recentRecipes.removeLast()
        }
    }
}