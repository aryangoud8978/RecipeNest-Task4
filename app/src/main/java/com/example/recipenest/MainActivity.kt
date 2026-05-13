package com.example.recipenest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipenest.components.ThemeManager
import com.example.recipenest.screens.*

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val darkMode = ThemeManager.isDarkMode.value

            MaterialTheme(

                colorScheme =
                    if (darkMode)
                        darkColorScheme()
                    else
                        lightColorScheme()

            ) {

                AppScreen()
            }
        }
    }
}

@Composable
fun AppScreen() {

    var showSplash by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(Unit) {

        Handler(Looper.getMainLooper()).postDelayed({
            showSplash = false
        }, 2000)
    }

    if (showSplash) {
        SplashScreen()
    } else {
        MainScreen()
    }
}

@Composable
fun MainScreen() {

    var selectedItem by remember {
        mutableIntStateOf(0)
    }

    var showDetailScreen by remember {
        mutableStateOf(false)
    }

    if (showDetailScreen) {

        Scaffold(

            topBar = {

                TopAppBar(

                    title = {
                        Text("Recipe Details")
                    },

                    navigationIcon = {

                        IconButton(
                            onClick = {
                                showDetailScreen = false
                            }
                        ) {

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }

        ) { paddingValues ->

            Box(
                modifier = Modifier.padding(paddingValues)
            ) {

                RecipeDetailScreen()
            }
        }

    } else {

        Scaffold(

            bottomBar = {

                NavigationBar {

                    NavigationBarItem(
                        selected = selectedItem == 0,
                        onClick = {
                            selectedItem = 0
                        },
                        icon = {
                            Icon(Icons.Default.Home, contentDescription = "Home")
                        },
                        label = {
                            Text("Home")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedItem == 1,
                        onClick = {
                            selectedItem = 1
                        },
                        icon = {
                            Icon(Icons.Default.Favorite, contentDescription = "Favorite")
                        },
                        label = {
                            Text("Favorites")
                        }
                    )

                    NavigationBarItem(
                        selected = selectedItem == 2,
                        onClick = {
                            selectedItem = 2
                        },
                        icon = {
                            Icon(Icons.Default.Person, contentDescription = "Profile")
                        },
                        label = {
                            Text("Profile")
                        }
                    )
                }
            }

        ) { paddingValues ->

            Box(
                modifier = Modifier.padding(paddingValues)
            ) {

                when (selectedItem) {

                    0 -> HomeScreen(
                        onRecipeClick = {
                            showDetailScreen = true
                        }
                    )

                    1 -> FavoriteScreen()

                    2 -> ProfileScreen()
                }
            }
        }
    }
}

@Composable
fun SplashScreen() {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF8F0)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "🍔",
                fontSize = 100.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "RecipeNest",
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6B00)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Delicious Recipes Everyday",
                fontSize = 18.sp,
                color = Color.Gray
            )
        }
    }
}