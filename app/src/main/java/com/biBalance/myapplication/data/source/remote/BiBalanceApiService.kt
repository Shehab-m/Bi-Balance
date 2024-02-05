package com.biBalance.myapplication.data.source.remote

import com.biBalance.myapplication.data.source.remote.model.BaseResponse
import com.biBalance.myapplication.data.source.remote.model.Level
import com.biBalance.myapplication.data.source.remote.model.LevelActivities
import com.biBalance.myapplication.data.source.remote.model.LoginResponse
import com.biBalance.myapplication.data.source.remote.model.UserData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface BiBalanceApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<BaseResponse<LoginResponse>>

    @GET("showLevelActivities/{id}")
    suspend fun getLevelActivities(
        @Path("id") id: Int
    ): Response<BaseResponse<LevelActivities>>

    @GET("homepage")
    suspend fun getHomeLevels(
    ): Response<BaseResponse<List<Level>>>

    @GET("getUserData")
    suspend fun getUserData(
    ): Response<BaseResponse<UserData>>
}