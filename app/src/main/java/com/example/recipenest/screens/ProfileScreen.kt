package com.example.recipenest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipenest.components.ThemeManager

@Composable
fun ProfileScreen(
    onLogout: () -> Unit
) {

    var showSettings by remember {
        mutableStateOf(false)
    }

    if (showSettings) {

        SettingsScreen()

    } else {

        val isDarkMode = ThemeManager.isDarkMode.value

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(20.dp),

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "👨‍🍳",
                fontSize = 100.sp
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Aryan Goud",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "aryangoud@example.com",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),

                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.surfaceVariant
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp),

                    verticalAlignment = Alignment.CenterVertically,

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {

                    Text(
                        text = "Dark Mode",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Switch(
                        checked = isDarkMode,

                        onCheckedChange = {
                            ThemeManager.isDarkMode.value = it
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showSettings = true
                    },

                shape = RoundedCornerShape(20.dp),

                colors = CardDefaults.cardColors(
                    containerColor =
                        MaterialTheme.colorScheme.surfaceVariant
                )
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                    horizontalArrangement =
                        Arrangement.SpaceBetween,

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text = "Settings",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium,
                        color =
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = "Settings"
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            ProfileCard(
                title = "Favorite Recipes",
                value = "12"
            )

            Spacer(modifier = Modifier.height(16.dp))

            ProfileCard(
                title = "Recipes Viewed",
                value = "48"
            )

            Spacer(modifier = Modifier.height(40.dp))

            Button(
                onClick = onLogout,

                modifier = Modifier.fillMaxWidth(),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Red
                )
            ) {

                Text(
                    text = "Logout",
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun ProfileCard(
    title: String,
    value: String
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.cardColors(
            containerColor =
                MaterialTheme.colorScheme.surfaceVariant
        ),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = title,
                fontSize = 18.sp,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = value,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFF6B00)
            )
        }
    }
}