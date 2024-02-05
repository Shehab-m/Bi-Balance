package com.biBalance.myapplication.data.repository

import com.biBalance.myapplication.data.source.remote.model.Level
import com.biBalance.myapplication.data.source.remote.model.LevelActivities
import com.biBalance.myapplication.data.source.remote.model.UserData

interface BiBalanceRepository {
    suspend fun loginUser(email:String,password: String)
    suspend fun getAccessToken(): String?
    suspend fun getHomeLevels(): List<Level>
    suspend fun getUserData(): UserData
    suspend fun getLevelActivities(id:Int): LevelActivities
}