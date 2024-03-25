package com.biBalance.myapplication.presentation.articles

import com.biBalance.myapplication.data.source.remote.model.Level

data class ArticlesUIState(
    val isLoadingUserData: Boolean = true,
    val isLoadingLevels: Boolean = true,
    val isError: Boolean = false,
    val error: Exception? = null,
    val posts: List<Level> = emptyList(),
    val userName: String = "",
    val totalScore: Int = 0,
){
    val isLoading = isLoadingUserData && isLoadingLevels
}