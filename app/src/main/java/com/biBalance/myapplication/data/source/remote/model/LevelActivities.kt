package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class LevelActivity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val typeName: String,
)