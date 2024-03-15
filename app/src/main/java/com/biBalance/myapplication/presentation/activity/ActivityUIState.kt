package com.biBalance.myapplication.presentation.activity

import com.biBalance.myapplication.util.ErrorHandler

data class ActivityUIState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val error: ErrorHandler = ErrorHandler.NoConnection,
    val isStartScreenVisible: Boolean = true,
    val isFinishScreenVisible: Boolean = false,
    val isActivityContentVisible: Boolean = false,
    val activityStateId: Int = -1,
    val activityDescription: List<String> = emptyList(),
    val activityTitle: String = "",
)