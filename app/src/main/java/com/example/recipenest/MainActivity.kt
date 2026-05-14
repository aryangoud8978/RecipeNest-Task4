package com.example.recipenest

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.recipenest.auth.LoginScreen
import com.example.recipenest.auth.SignupScreen
import com.example.recipenest.components.ThemeManager
import com.example.recipenest.screens.FavoriteScreen
import com.example.recipenest.screens.HomeScreen
import com.example.recipenest.screens.ProfileScreen
import com.example.recipenest.screens.RecipeDetailScreen
import com.example.recipenest.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            val darkMode =
                ThemeManager.isDarkMode.value

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

    val authViewModel: AuthViewModel =
        viewModel()

    var showSplash by remember {
        mutableStateOf(true)
    }

    var showSignup by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {

        Handler(
            Looper.getMainLooper()
        ).postDelayed({

            showSplash = false

        }, 2000)
    }

    if (showSplash) {

        SplashScreen()

    } else {

        if (authViewModel.isUserLoggedIn.value) {

            MainScreen(

                onLogout = {

                    authViewModel.logoutUser()
                }
            )

        } else {

            if (showSignup) {

                SignupScreen(

                    onSignupClick = { email, password ->

                        authViewModel.signupUser(
                            email,
                            password
                        )
                    },

                    onNavigateToLogin = {

                        showSignup = false
                    },

                    errorMessage =
                        authViewModel.authError.value,

                    isLoading =
                        authViewModel.isLoading.value
                )

            } else {

                LoginScreen(

                    onLoginClick = { email, password ->

                        authViewModel.loginUser(
                            email,
                            password
                        )
                    },

                    onNavigateToSignup = {

                        showSignup = true
                    },

                    errorMessage =
                        authViewModel.authError.value,

                    isLoading =
                        authViewModel.isLoading.value
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLogout: () -> Unit
) {

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

                        Text(
                            text = "Recipe Details"
                        )
                    },

                    navigationIcon = {

                        IconButton(

                            onClick = {

                                showDetailScreen = false
                            }
                        ) {

                            Icon(
                                imageVector =
                                    Icons.AutoMirrored.Filled.ArrowBack,

                                contentDescription = "Back"
                            )
                        }
                    }
                )
            }

        ) { paddingValues ->

            Box(
                modifier =
                    Modifier.padding(paddingValues)
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

                            Icon(
                                imageVector =
                                    Icons.Default.Home,

                                contentDescription = "Home"
                            )
                        },

                        label = {

                            Text(
                                text = "Home"
                            )
                        }
                    )

                    NavigationBarItem(

                        selected = selectedItem == 1,

                        onClick = {

                            selectedItem = 1
                        },

                        icon = {

                            Icon(
                                imageVector =
                                    Icons.Default.Favorite,

                                contentDescription = "Favorites"
                            )
                        },

                        label = {

                            Text(
                                text = "Favorites"
                            )
                        }
                    )

                    NavigationBarItem(

                        selected = selectedItem == 2,

                        onClick = {

                            selectedItem = 2
                        },

                        icon = {

                            Icon(
                                imageVector =
                                    Icons.Default.Person,

                                contentDescription = "Profile"
                            )
                        },

                        label = {

                            Text(
                                text = "Profile"
                            )
                        }
                    )
                }
            }

        ) { paddingValues ->

            Box(
                modifier =
                    Modifier.padding(paddingValues)
            ) {

                when (selectedItem) {

                    0 -> {

                        HomeScreen(

                            onRecipeClick = {

                                showDetailScreen = true
                            }
                        )
                    }

                    1 -> {

                        FavoriteScreen()
                    }

                    2 -> {

                        ProfileScreen(
                            onLogout = onLogout
                        )
                    }
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

            horizontalAlignment =
                Alignment.CenterHorizontally,

            verticalArrangement =
                Arrangement.Center
        ) {

            Text(
                text = "🍔",
                fontSize = 100.sp
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Text(

                text = "RecipeNest",

                fontSize = 36.sp,

                fontWeight = FontWeight.Bold,

                color = Color(0xFFFF6B00)
            )

            Spacer(
                modifier = Modifier.height(10.dp)
            )

            Text(

                text = "Delicious Recipes Everyday",

                fontSize = 18.sp,

                color = Color.Gray
            )
        }
    }
}