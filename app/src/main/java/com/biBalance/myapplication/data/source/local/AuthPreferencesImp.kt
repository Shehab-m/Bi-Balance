package com.biBalance.myapplication.data.source.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class AuthPreferencesImp @Inject constructor(
    private val dataStore: DataStore<Preferences>,
) : AuthPreferences {

    override var storedAccessToken: String? = null

    override suspend fun saveTokens(accessToken: String) {
        storedAccessToken = null
        dataStore.edit { preferences ->
            preferences[KEY_ACCESS_TOKEN] = accessToken
        }
    }

    override fun getAccessToken(): String? {
        return runBlocking {
            storedAccessToken =
                dataStore.data.map { preferences -> preferences[KEY_ACCESS_TOKEN] }.first()
            storedAccessToken
        }
    }
    override suspend fun clearToken() {
        storedAccessToken = null
        dataStore.edit { preferences ->
            preferences.remove(KEY_ACCESS_TOKEN)
        }
    }

    companion object {
        private const val PREFERENCES_FILE_NAME = "bi-balance"
        private val KEY_ACCESS_TOKEN = stringPreferencesKey("access_token")
    }

}