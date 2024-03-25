package com.biBalance.myapplication.presentation.profile

import androidx.lifecycle.viewModelScope
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.UserData
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<ProfileUIState, ProfileUIEffect>(ProfileUIState()),
    ProfileInteractionListener {

    init {
        getUserData()
    }

    override fun onClickLogout() {
        viewModelScope.launch {
            repository.logoutUser()
        }
        sendEffect(ProfileUIEffect.OnClickLogout)
    }

    override fun onClickActivities() {
        sendEffect(ProfileUIEffect.OnClickActivities)
    }

    override fun onClickWritings() {
        sendEffect(ProfileUIEffect.OnClickWritings)
    }

    override fun onClickPassword() {
        sendEffect(ProfileUIEffect.OnClickPassword)
    }

    override fun onClickTodo() {
        sendEffect(ProfileUIEffect.OnClickTodo)
    }

    override fun getUserData() {
        tryToExecute(
            { repository.getUserData() },
            ::onGetUserDataSuccess,
            ::onError
        )
    }

    override fun onClickArticles() {
        sendEffect(ProfileUIEffect.OnClickArticles)
    }

    private fun onGetUserDataSuccess(userData: UserData) {
        updateState {
            it.copy(userData = userData, isLoading = false)
        }
    }

    private fun onError(error: Exception) {
        _state.update { it.copy(isLoading = false) }
    }
}