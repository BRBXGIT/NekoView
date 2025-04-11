package com.example.data.local.data_store

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NekoViewDataStore(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "neko_view_data_store")

    private val userSessionTokenKey = stringPreferencesKey("user_session_token_key")
    val userSessionTokenFlow: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[userSessionTokenKey] ?: ""
        }

    suspend fun saveUserSessionToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[userSessionTokenKey] = token
        }
    }
}