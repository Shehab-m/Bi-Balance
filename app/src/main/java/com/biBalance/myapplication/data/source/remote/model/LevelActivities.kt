package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class LevelActivities (
    @SerializedName("levelActivities")
    val levelActivities: List<Activity>,
){
    data class Activity(
        @SerializedName("id")
        val id: Int,
        @SerializedName("type")
        val typeName: String,
    )
}
