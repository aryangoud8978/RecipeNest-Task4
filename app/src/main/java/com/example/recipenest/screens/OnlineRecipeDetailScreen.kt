package com.example.recipenest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.recipenest.components.ThemeManager
import com.example.recipenest.model.OnlineRecipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnlineRecipeDetailScreen(
    recipe: OnlineRecipe,
    onBackClick: () -> Unit
) {

    val darkMode = ThemeManager.isDarkMode.value

    val backgroundColor =
        if (darkMode) Color(0xFF121212)
        else Color(0xFFFFF8F0)

    val textColor =
        if (darkMode) Color.White
        else Color.Black

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .verticalScroll(rememberScrollState())
    ) {

        Box {

            AsyncImage(
                model = recipe.imageUrl,
                contentDescription = recipe.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp),
                contentScale = ContentScale.Crop
            )

            IconButton(
                onClick = onBackClick,
                modifier = Modifier
                    .padding(16.dp)
                    .background(
                        Color.Black.copy(alpha = 0.5f),
                        RoundedCornerShape(50)
                    )
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }
        }

        Column(
            modifier = Modifier.padding(20.dp)
        ) {

            Text(
                text = recipe.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            Surface(
                color = Color(0xFFFF6B00),
                shape = RoundedCornerShape(12.dp)
            ) {

                Text(
                    text = recipe.category,
                    modifier = Modifier.padding(
                        horizontal = 14.dp,
                        vertical = 8.dp
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Instructions",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = textColor
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = recipe.instructions,
                fontSize = 16.sp,
                lineHeight = 28.sp,
                color = textColor.copy(alpha = 0.8f)
            )

            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}