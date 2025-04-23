package com.example.data.local.data_store

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NekoViewDataStore(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(name = "neko_view_data_store")

    //Session key
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

    //Video quality key
    private val videoQualityKey = intPreferencesKey("video_quality_key")
    val videoQualityFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[videoQualityKey] ?: 480
        }

    suspend fun saveVideoQuality(quality: Int) {
        context.dataStore.edit { preferences ->
            preferences[videoQualityKey] = quality
        }
    }

    //Skip opening automatically key
    private val skipOpeningAutomaticallyKey = booleanPreferencesKey("skip_opening_automatically_key")
    val skipOpeningAutomatically: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[skipOpeningAutomaticallyKey] == true
        }

    suspend fun saveSkipOpeningAutomatically(skip: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[skipOpeningAutomaticallyKey] = skip
        }
    }
}