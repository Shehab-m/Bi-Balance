package com.biBalance.myapplication.data.source.remote

import com.biBalance.myapplication.data.source.remote.model.Activity
import com.biBalance.myapplication.data.source.remote.model.BaseResponse
import com.biBalance.myapplication.data.source.remote.model.Level
import com.biBalance.myapplication.data.source.remote.model.LevelActivities
import com.biBalance.myapplication.data.source.remote.model.LoginResponse
import com.biBalance.myapplication.data.source.remote.model.UserData
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface BiBalanceApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<BaseResponse<LoginResponse>>

    @FormUrlEncoded
    @POST("storeResult/{id}")
    suspend fun storeResult(
        @Path("id") id: Int,
        @Field("score") score: Int
    ): Response<BaseResponse<Any?>>

    @FormUrlEncoded
    @POST("storeResult/2")
    suspend fun sendScore(
        @Field("score") score: Int
    ): Response<Any> // Change the type as needed


    @GET("showLevelActivities/{id}")
    suspend fun getLevelActivities(
        @Path("id") id: Int
    ): Response<BaseResponse<LevelActivities>>
//    private const val API_KEY = "Content-Type"
//    private const val API_KEY_VALUE = "application/json; charset=utf-8"
//    @GET("showActivity/{id}")
//    @Header("Content-Type","application/json; charset=utf-8")
//    suspend fun getActivity(
//        @Path("id") id: Int
//    ): Response<BaseResponse<Activity>>
//    private const val API_KEY = "Content-Type"
//    private const val API_KEY_VALUE = "application/json; charset=utf-8"

    @GET("showActivity/{id}")
    suspend fun getActivity(
        @Header("Content-Type") apiKey: String = "application/json; charset=utf-8",
        @Path("id") id: Int
    ): Response<BaseResponse<Activity>>

    @GET("homepage")
    suspend fun getHomeLevels(
    ): Response<BaseResponse<List<Level>>>

    @GET("getUserData")
    suspend fun getUserData(
    ): Response<BaseResponse<UserData>>
}