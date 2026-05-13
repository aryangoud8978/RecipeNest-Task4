package com.example.recipenest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class FavoriteRecipe(
    val emoji: String,
    val name: String
)

@Composable
fun FavoriteScreen() {

    val favoriteRecipes = listOf(

        FavoriteRecipe("🍕", "Cheese Pizza"),
        FavoriteRecipe("🍔", "Chicken Burger"),
        FavoriteRecipe("🍜", "Spicy Noodles")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F0))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Favorite Recipes ❤️",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFF6B00)
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn {

            items(favoriteRecipes) { recipe ->

                FavoriteRecipeCard(recipe)

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun FavoriteRecipeCard(recipe: FavoriteRecipe) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = recipe.emoji,
                fontSize = 60.sp
            )

            Spacer(modifier = Modifier.width(20.dp))

            Column {

                Text(
                    text = recipe.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Saved to Favorites",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
            }
        }
    }
}