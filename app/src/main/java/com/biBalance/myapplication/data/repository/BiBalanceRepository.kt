package com.biBalance.myapplication.data.repository

interface BiBalanceRepository {
    suspend fun loginUser(email:String,password: String)
    suspend fun getAccessToken(): String?
}