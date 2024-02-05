package com.biBalance.myapplication.presentation.challenges

import com.biBalance.myapplication.data.source.remote.model.LevelActivities
import com.biBalance.myapplication.util.ErrorHandler

data class ChallengesUIState(
    val isLoadingUserData: Boolean = true,
    val isLoadingActivities: Boolean = true,
    val isError: Boolean = false,
    val error: ErrorHandler = ErrorHandler.NoConnection,
    val userName: String = "",
    val activities: LevelActivities = LevelActivities(emptyList()),
    val totalScore: Int = 0,
) {
    val isLoading = isLoadingUserData && isLoadingActivities
}