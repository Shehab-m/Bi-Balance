package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    val token: String,
    @SerializedName("role")
    val role : String?
)
