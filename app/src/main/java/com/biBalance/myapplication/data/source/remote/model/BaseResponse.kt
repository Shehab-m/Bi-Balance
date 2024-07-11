package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("data")
    val data: T,
    @SerializedName("success")
    val success: Boolean,
    @SerializedName("message")
    val message: Message,
)
