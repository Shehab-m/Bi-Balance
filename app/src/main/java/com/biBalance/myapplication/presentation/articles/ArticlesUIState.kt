package com.biBalance.myapplication.presentation.articles

import com.biBalance.myapplication.data.source.remote.model.UserPost

data class ArticlesUIState(
    val isLoadingUserData: Boolean = true,
    val isLoadingLevels: Boolean = true,
    val isError: Boolean = false,
    val error: Exception? = null,
    val posts: List<UserPost> = emptyList(),
    val userName: String = "",
    val totalScore: Int = 0,
){
    val isLoading = isLoadingUserData && isLoadingLevels
}