package com.biBalance.myapplication.presentation.activites

import com.biBalance.myapplication.data.source.remote.model.LevelActivities

data class ActivitiesUIState(
    val isLoadingUserData: Boolean = true,
    val isLoadingActivities: Boolean = true,
    val isError: Boolean = false,
    val error: Exception? = null,
    val userName: String = "",
    val activities: LevelActivities = LevelActivities(emptyList()),
    val totalScore: Int = 0,
) {
    val isLoading = isLoadingUserData && isLoadingActivities
}