package com.biBalance.myapplication.presentation.community

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.UserData
import com.biBalance.myapplication.data.source.remote.model.UserPost
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommunityViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<CommunityUIState, CommunityUIEffect>(CommunityUIState()),
    CommunityInteractionListener {

    init {
        getUserPosts()
        getUserData()
    }

    override fun getUserData(){
        tryToExecute(
            {repository.getUserData()},
            ::onGetUserDataSuccess,
            ::onError
        )
    }

    private fun onGetUserDataSuccess(userData: UserData) {
        updateState {
            it.copy(userName = userData.username, totalScore = userData.totalScore ,isLoadingUserData = false)
        }
    }

    override fun onClickAddWriting() {

        _state.update { it.copy(isWritingScreenVisible = true, writings = "") }
    }

    override fun onClickBackFromWriting() {
        _state.update { it.copy(isWritingScreenVisible = false) }
    }

    override fun onWritingsInputChange(text: String) {
        _state.update { it.copy(writings = text) }
    }

    override fun onClickSaveWriting() {
        _state.update { it.copy(isWritingScreenVisible = false) }
        viewModelScope.launch {
            repository.savePost(_state.value.writings.ifEmpty { "" })
        }
        getUserPosts()
    }

    override fun onClickPostLike(postId: Int, isLiked: Boolean) {
        if (isLiked) {
            onClickUnLike(postId)
        } else {
            onClickLike(postId)
        }
    }

    private fun onClickLike(postId: Int) {
        viewModelScope.launch {
            repository.likeUserPost(postId)
        }
    }

    private fun onClickUnLike(postId: Int) {
        viewModelScope.launch {
            repository.unlikeUserPost(postId)
        }
    }

    fun getUserPosts() {
        updateState { it.copy(isLoadingPosts = true, posts = emptyList()) }
        Log.d("vkemviermvermveo", "try to ex")
        tryToExecute(
            { repository.getArticlePosts() },
            ::onGetUserPostsSuccess,
            ::onError
        )
    }

    private fun onGetUserPostsSuccess(userPosts: List<UserPost>?) {
        Log.d("vkemviermvermveo", userPosts.toString())
        updateState {
            it.copy(posts = userPosts ?: emptyList(), isLoadingPosts = false)
        }
    }

    private fun onError(error: Exception) {
        Log.d("vkemviermvermveo", error.message.toString())
        _state.update {
            it.copy(
                isLoadingPosts = false,
                isLoadingUserData = false,
                isError = true,
                error = error,
            )
        }
    }

}