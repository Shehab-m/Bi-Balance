package com.biBalance.myapplication.presentation.chat

import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.Level
import com.biBalance.myapplication.data.source.remote.model.UserData
import com.biBalance.myapplication.presentation.base.BaseViewModel
import com.biBalance.myapplication.util.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<ChatUIState, ChatUIEffect>(ChatUIState()),
    ChatInteractionListener {

    init {
        getHomeLevels()
        getUserData()
    }
    override fun onClickLike(postId: Int) {
        sendEffect(ChatUIEffect.OnClickLevel(postId))
    }

    private fun getHomeLevels(){
        tryToExecute(
            {repository.getHomeLevels()},
            ::onGetHomeLevelsSuccess,
            ::onError
        )
    }

    private fun onGetHomeLevelsSuccess(levels:List<Level>) {
        updateState { it.copy(levels = levels, isLoadingLevels = false) }
    }

    private fun getUserData(){
        tryToExecute(
            {repository.getUserData()},
            ::onGetUserDataSuccess,
            ::onError
        )
    }

    private fun onGetUserDataSuccess(userData:UserData) {
        updateState {
            it.copy(userName = userData.username, totalScore = userData.totalScore ,isLoadingUserData = false)
        }
    }
    private fun onError(error: ErrorHandler) {
        _state.update { it.copy(isLoadingLevels = false, isLoadingUserData = false, isError = true, error = error,) }
    }

}