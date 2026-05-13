package com.example.recipenest.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNav(

    val title: String,
    val icon: ImageVector
) {

    object Home : BottomNav(
        "Home",
        Icons.Default.Home
    )

    object Favorite : BottomNav(
        "Favorites",
        Icons.Default.Favorite
    )

    object Profile : BottomNav(
        "Profile",
        Icons.Default.Person
    )
}