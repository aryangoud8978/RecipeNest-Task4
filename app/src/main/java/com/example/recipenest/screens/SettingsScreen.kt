package com.example.recipenest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.recipenest.components.SettingsManager
import com.example.recipenest.utils.SettingsDataStore
import kotlinx.coroutines.launch
import androidx.compose.runtime.rememberCoroutineScope

@Composable
fun SettingsScreen() {

    val context = LocalContext.current

    val dataStore = SettingsDataStore(context)

    val coroutineScope = rememberCoroutineScope()

    val notificationsEnabled by
    dataStore.notificationsEnabled.collectAsState(
        initial = true
    )

    val autoRefreshEnabled by
    dataStore.autoRefreshEnabled.collectAsState(
        initial = true
    )

    LaunchedEffect(
        notificationsEnabled,
        autoRefreshEnabled
    ) {

        SettingsManager.notificationsEnabled.value =
            notificationsEnabled

        SettingsManager.autoRefreshEnabled.value =
            autoRefreshEnabled
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Settings",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(30.dp))

        SettingsItem(
            title = "Enable Notifications",

            checked = notificationsEnabled,

            onCheckedChange = {

                coroutineScope.launch {

                    dataStore.saveNotificationsEnabled(
                        it
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(20.dp))

        SettingsItem(
            title = "Auto Refresh Recipes",

            checked = autoRefreshEnabled,

            onCheckedChange = {

                coroutineScope.launch {

                    dataStore.saveAutoRefreshEnabled(
                        it
                    )
                }
            }
        )
    }
}

@Composable
fun SettingsItem(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),

        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),

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
                text = title,
                fontSize = 18.sp,
                color =
                    MaterialTheme.colorScheme.onSurfaceVariant
            )

            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}