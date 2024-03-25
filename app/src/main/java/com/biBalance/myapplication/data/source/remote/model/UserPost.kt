package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class UserPost(
    @SerializedName("id")
    val id: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("body")
    val body: String,
    @SerializedName("likes_count")
    val likesCount: Int
)
