package com.biBalance.myapplication.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class ActivitiesScores(
    @SerializedName("mental") val mentalScore: Int,
    @SerializedName("social") val socialScore: Int,
    @SerializedName("emotional") val emotionalScore: Int,
    @SerializedName("physical") val physicalScore: Int,
)

data class ActivitiesData(
    @SerializedName("activities") val activitiesScores: ActivitiesScores
)