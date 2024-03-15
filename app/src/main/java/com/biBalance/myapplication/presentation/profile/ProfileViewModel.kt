package com.biBalance.myapplication.presentation.profile

import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.UserData
import com.biBalance.myapplication.presentation.base.BaseViewModel
import com.biBalance.myapplication.util.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
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

    private fun getUserData() {
        tryToExecute(
            { repository.getUserData() },
            ::onGetUserDataSuccess,
            ::onError
        )
    }

    private fun onGetUserDataSuccess(userData: UserData) {
        updateState {
            it.copy(userData = userData, isLoading = false)
        }
    }

    private fun onError(error: ErrorHandler) {
        _state.update { it.copy(isLoading = false) }
    }
}