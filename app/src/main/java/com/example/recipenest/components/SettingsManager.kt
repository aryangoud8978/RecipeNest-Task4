package com.example.recipenest.components

import androidx.compose.runtime.mutableStateOf

object SettingsManager {

    val notificationsEnabled =
        mutableStateOf(true)

    val autoRefreshEnabled =
        mutableStateOf(true)
}