package com.example.recipenest.screens

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipenest.api.RetrofitInstance
import com.example.recipenest.components.CategoryChip
import com.example.recipenest.components.FeaturedBanner
import com.example.recipenest.components.OnlineRecipeCard
import com.example.recipenest.components.ShimmerRecipeCard
import com.example.recipenest.components.ThemeManager
import com.example.recipenest.model.OnlineRecipe
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalMaterialApi::class,
    ExperimentalAnimationApi::class
)
@Composable
fun HomeScreen(
    onRecipeClick: () -> Unit
) {

    var selectedRecipe by remember {
        mutableStateOf<OnlineRecipe?>(null)
    }

    AnimatedContent(
        targetState = selectedRecipe,

        transitionSpec = {

            (
                    slideInHorizontally(
                        initialOffsetX = { fullWidth ->
                            fullWidth
                        }
                    ) + fadeIn()
                    ).togetherWith(

                    slideOutHorizontally(
                        targetOffsetX = { fullWidth ->
                            -fullWidth
                        }
                    ) + fadeOut()
                )
        },

        label = "recipe_transition"
    ) { recipeState ->

        if (recipeState != null) {

            OnlineRecipeDetailScreen(
                recipe = recipeState,
                onBackClick = {
                    selectedRecipe = null
                }
            )

        } else {

            HomeContent(
                onRecipeSelected = {
                    selectedRecipe = it
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeContent(
    onRecipeSelected: (OnlineRecipe) -> Unit
) {

    var searchText by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf("All")
    }

    var errorMessage by remember {
        mutableStateOf<String?>(null)
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

    var recipes by remember {
        mutableStateOf<List<OnlineRecipe>>(emptyList())
    }

    var isLoading by remember {
        mutableStateOf(true)
    }

    var isRefreshing by remember {
        mutableStateOf(false)
    }

    val scope = rememberCoroutineScope()

    fun fetchRecipes() {

        scope.launch {

            try {

                errorMessage = null

                if (!isRefreshing) {
                    isLoading = true
                }

                val response =
                    RetrofitInstance.api.getRecipes()

                recipes = response.meals.map {

                    OnlineRecipe(
                        id = it.idMeal,
                        name = it.strMeal,
                        imageUrl = it.strMealThumb,
                        category = it.strCategory,
                        instructions = it.strInstructions
                    )
                }

            } catch (e: Exception) {

                errorMessage =
                    "Failed to load recipes. Check internet connection."

                e.printStackTrace()

            } finally {

                isLoading = false
                isRefreshing = false
            }
        }
    }

    LaunchedEffect(Unit) {

        fetchRecipes()
    }

    val pullRefreshState = rememberPullRefreshState(
        refreshing = isRefreshing,
        onRefresh = {

            isRefreshing = true
            fetchRecipes()
        }
    )

    val categoryList = listOf(
        "All",
        "Chicken",
        "Seafood",
        "Dessert",
        "Vegetarian",
        "Pasta",
        "Beef"
    )

    val filteredRecipes = recipes.filter { recipe ->

        val matchesSearch =
            recipe.name.contains(
                searchText,
                ignoreCase = true
            )

        val matchesCategory =
            selectedCategory == "All" ||
                    recipe.category.equals(
                        selectedCategory,
                        ignoreCase = true
                    )

        matchesSearch && matchesCategory
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .pullRefresh(pullRefreshState)
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),

            verticalArrangement = Arrangement.spacedBy(16.dp),

            contentPadding = PaddingValues(
                top = 20.dp,
                bottom = 120.dp
            )
        ) {

            item {

                Row(
                    modifier = Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Column {

                        Text(
                            text = "Hello Aryan 👋",
                            fontSize = 18.sp,
                            color = subtitleColor
                        )

                        Spacer(
                            modifier = Modifier.height(4.dp)
                        )

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
                                imageVector =
                                    Icons.Default.DarkMode,

                                contentDescription =
                                    "Dark Mode",

                                tint = Color(0xFFFF6B00),

                                modifier = Modifier.size(30.dp)
                            )
                        }

                        IconButton(
                            onClick = { }
                        ) {

                            Icon(
                                imageVector =
                                    Icons.Default.Notifications,

                                contentDescription =
                                    "Notifications",

                                tint = Color(0xFFFF6B00),

                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
            }

            item {

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
            }

            item {

                FeaturedBanner()
            }

            item {

                Text(
                    text = "Categories",

                    fontSize = 22.sp,

                    fontWeight = FontWeight.Bold,

                    color = textColor
                )
            }

            item {

                Row(
                    modifier = Modifier.horizontalScroll(
                        rememberScrollState()
                    )
                ) {

                    categoryList.forEach { category ->

                        CategoryChip(
                            text = category,

                            isSelected =
                                selectedCategory == category,

                            onClick = {
                                selectedCategory = category
                            }
                        )

                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )
                    }
                }
            }

            item {

                Text(
                    text = "Online Recipes",

                    fontSize = 22.sp,

                    fontWeight = FontWeight.Bold,

                    color = textColor
                )
            }

            when {

                isLoading -> {

                    items(5) {

                        ShimmerRecipeCard()
                    }
                }

                errorMessage != null -> {

                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 50.dp),

                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "⚠️",
                                fontSize = 50.sp
                            )

                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )

                            Text(
                                text = errorMessage!!,
                                color = textColor
                            )

                            Spacer(
                                modifier = Modifier.height(20.dp)
                            )

                            Button(
                                onClick = {
                                    fetchRecipes()
                                }
                            ) {

                                Text(text = "Retry")
                            }
                        }
                    }
                }

                filteredRecipes.isEmpty() -> {

                    item {

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 50.dp),

                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {

                            Text(
                                text = "🍽️",
                                fontSize = 50.sp
                            )

                            Spacer(
                                modifier = Modifier.height(16.dp)
                            )

                            Text(
                                text = "No recipes found",
                                color = textColor
                            )
                        }
                    }
                }

                else -> {

                    items(filteredRecipes) { recipe ->

                        OnlineRecipeCard(
                            recipe = recipe,

                            onClick = {
                                onRecipeSelected(recipe)
                            }
                        )
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = isRefreshing,

            state = pullRefreshState,

            modifier = Modifier.align(
                Alignment.TopCenter
            )
        )
    }
}