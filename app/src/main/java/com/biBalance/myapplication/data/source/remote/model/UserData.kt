package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("username")
    val username: String,
    @SerializedName("totalScore")
    val totalScore: Int
)