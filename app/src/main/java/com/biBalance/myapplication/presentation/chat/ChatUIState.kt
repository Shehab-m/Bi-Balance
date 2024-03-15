package com.biBalance.myapplication.presentation.chat

import com.biBalance.myapplication.data.source.remote.model.Level
import com.biBalance.myapplication.util.ErrorHandler

data class ChatUIState(
    val isLoadingUserData: Boolean = true,
    val isLoadingLevels: Boolean = true,
    val isError: Boolean = false,
    val error: ErrorHandler = ErrorHandler.NoConnection,
    val levels: List<Level> = emptyList(),
    val userName: String = "",
    val totalScore: Int = 0,
){
    val isLoading = isLoadingUserData && isLoadingLevels
}