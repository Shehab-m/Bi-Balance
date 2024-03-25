package com.biBalance.myapplication.presentation.activites

import androidx.lifecycle.SavedStateHandle
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.LevelActivities
import com.biBalance.myapplication.data.source.remote.model.UserData
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ActivitiesViewModel @Inject constructor(
    private val repository: BiBalanceRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ActivitiesUIState, ActivitiesUIEffect>(ActivitiesUIState()),
    ActivitiesInteractionListener {

    private val activitiesArgs = ActivitiesArgs(savedStateHandle)

    init {
        getUserData()
        getLevelActivities()
    }

    override fun onClickActivity(levelId: Int) {
        sendEffect(ActivitiesUIEffect.OnClickActivity(levelId))
    }

    override fun getUserData() {
        tryToExecute(
            { repository.getUserData() },
            ::onGetUserDataSuccess,
            ::onError
        )
    }

    override fun onClickBack() {
        sendEffect(ActivitiesUIEffect.OnClickBack)
    }

    private fun onGetUserDataSuccess(userData: UserData) {
        updateState {
            it.copy(
                userName = userData.username,
                totalScore = userData.totalScore,
                isLoadingUserData = false
            )
        }
    }

    private fun getLevelActivities() {
        tryToExecute(
            { repository.getLevelActivities(activitiesArgs.activitiesId.toInt()) },
            ::onGetLevelActivitiesSuccess,
            ::onError
        )
    }

    private fun onGetLevelActivitiesSuccess(activities: LevelActivities) {
        updateState { it.copy(activities = activities, isLoadingActivities = false) }
    }

    private fun onError(error: Exception) {
        _state.update { it.copy(isLoadingUserData = false,isLoadingActivities = false, isError = true, error = error) }
    }

}