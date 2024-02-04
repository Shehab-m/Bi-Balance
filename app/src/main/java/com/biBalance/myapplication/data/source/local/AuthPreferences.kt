package com.biBalance.myapplication.data.source.local

interface AuthPreferences {
    suspend fun clearToken()
    suspend fun saveTokens(accessToken: String)
    fun getAccessToken(): String?
    val storedAccessToken: String?
}