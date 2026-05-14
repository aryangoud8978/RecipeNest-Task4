package com.example.recipenest.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipenest.model.RecentRecipe

@Composable
fun RecentRecipeCard(
    recipe: RecentRecipe,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .width(180.dp)
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),

        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {

        Column {

            AsyncImage(
                model = recipe.imageUrl,

                contentDescription = recipe.name,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 20.dp,
                            topEnd = 20.dp
                        )
                    ),

                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(12.dp),

                verticalArrangement =
                    Arrangement.spacedBy(6.dp)
            ) {

                Text(
                    text = recipe.name,

                    fontWeight = FontWeight.Bold,

                    maxLines = 1,

                    color =
                        MaterialTheme.colorScheme.onSurfaceVariant
                )

                Text(
                    text = recipe.category,

                    color =
                        MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}