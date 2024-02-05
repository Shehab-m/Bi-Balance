package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class Level (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: List<LevelStatus>,
){
    data class LevelStatus(
        @SerializedName("score")
        val score: Int,
        @SerializedName("unlocked")
        val unlocked: Int,
    )
}

