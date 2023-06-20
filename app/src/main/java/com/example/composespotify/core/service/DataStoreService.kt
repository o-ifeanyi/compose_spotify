package com.example.composespotify.core.service

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import java.time.LocalDateTime

class DataStoreService(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("store")
        private val TOKEN = stringPreferencesKey("token")
        private val TOKEN_EXP = stringPreferencesKey("token_exp")
    }


    suspend fun getAccessToken(): String {
        val pref = context.dataStore.data.first()
        return  pref[TOKEN] ?: ""
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN] = token
        }
    }

    suspend fun saveTokenExp(date: LocalDateTime) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_EXP] = date.toString()
        }
    }

    suspend fun getTokenExp(): LocalDateTime? {
        val pref = context.dataStore.data.first()
        val dateString = pref[TOKEN_EXP]
        if (dateString.isNullOrEmpty()) return null

        return LocalDateTime.parse(dateString)
    }
}