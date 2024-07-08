package com.biBalance.myapplication.presentation.activity

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.Activity
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val repository: BiBalanceRepository,
    savedStateHandle: SavedStateHandle
) : BaseViewModel<ActivityUIState, ActivityUIEffect>(ActivityUIState()),
    ActivityInteractionListener {

    private val activityArgs = ActivityArgs(savedStateHandle)

    init {
        updateState { it.copy(activityStateId = activityArgs.activityId.toInt()) }
        getActivity()
    }

    private fun getActivity() {
        tryToExecute(
            { repository.getActivity(activityArgs.activityId.toInt()) },
            ::onGetActivitySuccess,
            ::onError
        )
    }

    private fun onGetActivitySuccess(activity: Activity) {
        updateState {
            it.copy(
                activityDescription = activity.activityDescription,
                activityTitle = activity.activityTitle,
                isLoading = false
            )
        }
    }

    private fun onError(error: Exception) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = true,
                error = error
            )
        }
    }

    override fun showActivityContent() {
        _state.update { it.copy(isActivityContentVisible = true,isStartScreenVisible = false) }
    }

    override fun onClickNextActivity() {
        _state.update { it.copy(isStartScreenVisible = true) }
    }

    override fun showFinishScreen() {
        updateState { it.copy(isFinishScreenVisible = true, isActivityContentVisible = false) }
    }

    override fun onClickNextFinishScreen() {
        viewModelScope.launch {
            repository.storeResult( activityArgs.activityId.toInt(),1)
        }
        sendEffect(ActivityUIEffect.GoToActivitiesScreen)
    }

    override fun onClickBack() {
        sendEffect(ActivityUIEffect.OnClickBack)
    }

}