package com.biBalance.myapplication.presentation.challenges

import androidx.lifecycle.SavedStateHandle
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.LevelActivities
import com.biBalance.myapplication.data.source.remote.model.UserData
import com.biBalance.myapplication.presentation.base.BaseViewModel
import com.biBalance.myapplication.util.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ChallengesViewModel @Inject constructor(
    private val repository: BiBalanceRepository,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel<ChallengesUIState, ChallengesUIEffect>(ChallengesUIState()),
    ChallengesInteractionListener {

    private val activitiesArgs = ActivitiesArgs(savedStateHandle)

    init {
        getUserData()
        getLevelActivities()
    }

    override fun onClickChallenge(levelId: Int) {
        sendEffect(ChallengesUIEffect.OnClickChallenge(1))
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

    private fun onError(error: ErrorHandler) {
        _state.update { it.copy(isLoadingUserData = false,isLoadingActivities = false, isError = true, error = error) }
    }

}