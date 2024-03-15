package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("writing")
    val writing: String,
    @SerializedName("status")
    val likesCount: Int,
)
