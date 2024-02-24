package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class Activity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("type")
    val typeName: String,
    @SerializedName("description")
    val activityDescription: List<String>,
    @SerializedName("title")
    val activityTitle: String,
)
