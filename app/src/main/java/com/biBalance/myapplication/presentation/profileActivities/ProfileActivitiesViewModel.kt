package com.biBalance.myapplication.presentation.profileActivities

import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.ActivitiesScores
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProfileActivitiesViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<ProfileActivitiesUIState, ProfileActivitiesUIEffect>(ProfileActivitiesUIState()),
    ProfileActivitiesInteractionListener {

    init {
        getActivitiesScores()
    }

    override fun onClickBack() {
        sendEffect(ProfileActivitiesUIEffect.OnClickBack)
    }

    private fun getActivitiesScores() {
        tryToExecute(
            { repository.getActivitiesScores() },
            ::onGetActivitiesScoresSuccess,
            ::onError
        )
    }

    private fun onGetActivitiesScoresSuccess(scores: ActivitiesScores) {
        updateState {
            it.copy(scores = scores, isLoading = false)
        }
    }

    private fun onError(error: Exception) {
        _state.update { it.copy(isLoading = false) }
    }
}
