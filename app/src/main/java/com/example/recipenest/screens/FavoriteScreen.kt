package com.example.recipenest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipenest.api.RecipeDatabase
import com.example.recipenest.components.OnlineRecipeCard
import com.example.recipenest.components.ThemeManager
import com.example.recipenest.model.OnlineRecipe

@Composable
fun FavoriteScreen() {

    val darkMode = ThemeManager.isDarkMode.value

    val backgroundColor =
        if (darkMode)
            Color(0xFF121212)
        else
            Color(0xFFFFF8F0)

    val textColor =
        if (darkMode)
            Color.White
        else
            Color.Black

    val context = LocalContext.current

    val database =
        RecipeDatabase.getDatabase(context)

    val dao = database.favoriteRecipeDao()

    val favoriteRecipes by dao
        .getAllFavorites()
        .collectAsState(initial = emptyList())

    val convertedRecipes = favoriteRecipes.map {

        OnlineRecipe(
            id = it.id,
            name = it.name,
            imageUrl = it.imageUrl,
            category = it.category,
            instructions = it.instructions
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Favorite Recipes ❤️",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (convertedRecipes.isEmpty()) {

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "No favorite recipes yet 🍽️",
                    style = MaterialTheme.typography.titleMedium,
                    color = textColor.copy(alpha = 0.7f)
                )
            }

        } else {

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(
                    bottom = 100.dp
                )
            ) {

                items(convertedRecipes) { recipe ->

                    OnlineRecipeCard(
                        recipe = recipe,
                        onClick = { }
                    )
                }
            }
        }
    }
}