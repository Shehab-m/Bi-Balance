package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class TaskCreated(
    @SerializedName("id")val id: Int,
    @SerializedName("task1") val task1: String,
    @SerializedName("task2") val task2: String,
    @SerializedName("task3") val task3: String,
    @SerializedName("task4") val task4: String,
    @SerializedName("created_at") val creationDate: String,
)