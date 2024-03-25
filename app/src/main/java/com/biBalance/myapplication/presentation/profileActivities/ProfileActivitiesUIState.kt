package com.biBalance.myapplication.presentation.profileActivities

import com.biBalance.myapplication.data.source.remote.model.ActivitiesScores

data class ProfileActivitiesUIState(
    val isLoading: Boolean = false,
    val scores: ActivitiesScores = ActivitiesScores(0,0,0,0)
)