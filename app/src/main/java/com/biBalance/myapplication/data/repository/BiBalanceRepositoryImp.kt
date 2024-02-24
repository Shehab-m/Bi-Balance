package com.biBalance.myapplication.data.repository

import android.util.Log
import com.biBalance.myapplication.data.source.local.AuthPreferences
import com.biBalance.myapplication.data.source.remote.BiBalanceApiService
import com.biBalance.myapplication.data.source.remote.model.Activity
import com.biBalance.myapplication.data.source.remote.model.BaseResponse
import com.biBalance.myapplication.data.source.remote.model.Level
import com.biBalance.myapplication.data.source.remote.model.LevelActivities
import com.biBalance.myapplication.data.source.remote.model.UserData
import retrofit2.Response
import javax.inject.Inject

class BiBalanceRepositoryImp @Inject constructor(
    private val biBalanceService: BiBalanceApiService,
    private val datastore: AuthPreferences,
): BiBalanceRepository {

    override suspend fun loginUser(email:String,password: String) {
        val response = wrap {biBalanceService.loginUser(email = email,password = password)}.data
        datastore.saveTokens(response.token)
    }

    override suspend fun getHomeLevels(): List<Level> {
        return wrap {biBalanceService.getHomeLevels()}.data
    }

    override suspend fun getLevelActivities(id:Int): LevelActivities {
        return wrap {biBalanceService.getLevelActivities(id)}.data
    }
    override suspend fun getActivity(id:Int): Activity {
        return wrap {biBalanceService.getActivity(id)}.data
    }
    override suspend fun getUserData(): UserData {
        return wrap {biBalanceService.getUserData()}.data
    }

    override suspend fun getAccessToken(): String? {
        return datastore.getAccessToken()
    }

    private suspend fun <T> wrap(function: suspend () -> Response<BaseResponse<T>>): BaseResponse<T> {
        try {
            val response = function().body()!!
            return if (response.success) {
                Log.d("Tag", "repository done correctly")
                response
            } else {
                Log.d("Tag", "repository failed :${response.message}")
                throw Exception("${response.message}")
            }
        } catch (e: Exception) {
            Log.e("Tag", "response Error:${e.message}")
            throw Exception("${e.message}")
        }
    }
}