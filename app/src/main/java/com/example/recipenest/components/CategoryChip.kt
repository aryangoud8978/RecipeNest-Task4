package com.example.recipenest.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CategoryChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor =
        if (isSelected)
            Color(0xFFFF6B00)
        else
            MaterialTheme.colorScheme.surfaceVariant

    val textColor =
        if (isSelected)
            Color.White
        else
            MaterialTheme.colorScheme.onSurfaceVariant

    Surface(
        modifier = Modifier.clickable {
            onClick()
        },
        shape = RoundedCornerShape(50.dp),
        color = backgroundColor,
        tonalElevation = 4.dp
    ) {

        Text(
            text = text,
            modifier = Modifier.padding(
                horizontal = 18.dp,
                vertical = 10.dp
            ),
            color = textColor,
            fontWeight = FontWeight.SemiBold
        )
    }
}