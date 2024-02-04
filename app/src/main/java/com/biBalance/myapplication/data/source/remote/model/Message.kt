package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("success message")
    val successMessage: Int
)
