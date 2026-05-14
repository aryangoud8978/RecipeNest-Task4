package com.example.recipenest.utils

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(
    name = "settings"
)

class SettingsDataStore(
    private val context: Context
) {

    companion object {

        val NOTIFICATIONS_KEY =
            booleanPreferencesKey(
                "notifications_enabled"
            )

        val AUTO_REFRESH_KEY =
            booleanPreferencesKey(
                "auto_refresh_enabled"
            )
    }

    val notificationsEnabled: Flow<Boolean> =
        context.dataStore.data.map { preferences ->

            preferences[NOTIFICATIONS_KEY] ?: true
        }

    val autoRefreshEnabled: Flow<Boolean> =
        context.dataStore.data.map { preferences ->

            preferences[AUTO_REFRESH_KEY] ?: true
        }

    suspend fun saveNotificationsEnabled(
        enabled: Boolean
    ) {

        context.dataStore.edit { preferences ->

            preferences[NOTIFICATIONS_KEY] =
                enabled
        }
    }

    suspend fun saveAutoRefreshEnabled(
        enabled: Boolean
    ) {

        context.dataStore.edit { preferences ->

            preferences[AUTO_REFRESH_KEY] =
                enabled
        }
    }
}