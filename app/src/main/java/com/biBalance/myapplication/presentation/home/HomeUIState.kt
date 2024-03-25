package com.biBalance.myapplication.presentation.home

import com.biBalance.myapplication.data.source.remote.model.Level

data class HomeUIState(
    val isLoadingUserData: Boolean = true,
    val isLoadingLevels: Boolean = true,
    val isError: Boolean = false,
    val error: Exception? = null,
    val levels: List<Level> = emptyList(),
    val userName: String = "",
    val totalScore: Int = 0,
){
    val isLoading = isLoadingUserData && isLoadingLevels
}