package com.biBalance.myapplication.presentation.activity

data class ActivityUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: Exception? = null,
    val isStartScreenVisible: Boolean = true,
    val isFinishScreenVisible: Boolean = false,
    val isActivityContentVisible: Boolean = false,
    val activityStateId: Int = -1,
    val activityDescription: List<String> = emptyList(),
    val activityTitle: String = "",
)