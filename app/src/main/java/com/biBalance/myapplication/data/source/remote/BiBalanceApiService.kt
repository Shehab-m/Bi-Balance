package com.biBalance.myapplication.data.source.remote

import com.biBalance.myapplication.data.source.remote.model.BaseResponse
import com.biBalance.myapplication.data.source.remote.model.LoginResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface BiBalanceApiService {
    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<BaseResponse<LoginResponse>>
}