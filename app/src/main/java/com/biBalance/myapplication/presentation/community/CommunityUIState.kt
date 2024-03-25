package com.biBalance.myapplication.presentation.community

import com.biBalance.myapplication.data.source.remote.model.UserPost

data class CommunityUIState(
    val isLoadingUserData: Boolean = true,
    val isLoadingPosts: Boolean = true,
    val isError: Boolean = false,
    val error: Exception? = null,
    val posts: List<UserPost> = emptyList(),
    val userName: String = "",
    val totalScore: Int = 0,
    val writings: String = "",
    val isWritingScreenVisible: Boolean = false,
){
    val isLoading = isLoadingUserData && isLoadingPosts
}