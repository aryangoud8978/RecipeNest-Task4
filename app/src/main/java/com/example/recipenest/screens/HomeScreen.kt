package com.example.recipenest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipenest.components.CategoryChip
import com.example.recipenest.components.FeaturedBanner
import com.example.recipenest.components.RecipeCard
import com.example.recipenest.components.ThemeManager
import com.example.recipenest.model.Recipe

@Composable
fun HomeScreen(
    onRecipeClick: () -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }

    val darkMode = ThemeManager.isDarkMode.value

    val backgroundColor =
        if (darkMode) Color(0xFF121212)
        else Color(0xFFFFF8F0)

    val textColor =
        if (darkMode) Color.White
        else Color.Black

    val subtitleColor =
        if (darkMode) Color.LightGray
        else Color.Gray

    val recipeList = listOf(

        Recipe("🍕", "Cheese Pizza", "20 min", "4.8 ⭐"),
        Recipe("🍔", "Chicken Burger", "15 min", "4.7 ⭐"),
        Recipe("🍜", "Spicy Noodles", "18 min", "4.9 ⭐"),
        Recipe("🥗", "Healthy Salad", "10 min", "4.5 ⭐")
    )

    val filteredRecipes = recipeList.filter {

        it.name.contains(
            searchText,
            ignoreCase = true
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "Hello Aryan 👋",
                    fontSize = 18.sp,
                    color = subtitleColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "RecipeNest",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF6B00)
                )
            }

            Row {

                IconButton(
                    onClick = {

                        ThemeManager.isDarkMode.value =
                            !ThemeManager.isDarkMode.value
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.DarkMode,
                        contentDescription = "Dark Mode",
                        tint = Color(0xFFFF6B00),
                        modifier = Modifier.size(30.dp)
                    )
                }

                IconButton(
                    onClick = { }
                ) {

                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color(0xFFFF6B00),
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = {
                searchText = it
            },
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(text = "Search Recipes")
            },
            shape = RoundedCornerShape(16.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        FeaturedBanner()

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Categories",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.horizontalScroll(
                rememberScrollState()
            )
        ) {

            CategoryChip("🍕 Pizza")

            Spacer(modifier = Modifier.width(12.dp))

            CategoryChip("🍔 Burger")

            Spacer(modifier = Modifier.width(12.dp))

            CategoryChip("🥗 Salad")

            Spacer(modifier = Modifier.width(12.dp))

            CategoryChip("🍜 Noodles")
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text(
            text = "Popular Recipes",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = textColor
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {

            items(filteredRecipes) { recipe ->

                RecipeCard(
                    recipe = recipe,
                    onClick = {
                        onRecipeClick()
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}