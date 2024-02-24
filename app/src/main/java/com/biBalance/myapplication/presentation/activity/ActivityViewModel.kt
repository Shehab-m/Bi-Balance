package com.biBalance.myapplication.presentation.activity

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.Activity
import com.biBalance.myapplication.presentation.base.BaseViewModel
import com.biBalance.myapplication.util.ErrorHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
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
        Log.d("vefvefvefdvevevferve", "activity".toString())
        getActivity()
        Log.d("vefvefvefdvevevferve", "activity".toString())
    }

    private fun getActivity() {
        tryToExecute(
            { repository.getActivity(activityArgs.activityId.toInt()) },
            ::onGetActivitySuccess,
            ::onError
        )
    }

    private fun onGetActivitySuccess(activity: Activity) {
        Log.d("vefvefvefdvevevferve", activity.toString())
        updateState {
            it.copy(
                activityDescription = activity.activityDescription,
                activityTitle = activity.activityTitle,
                isLoading = false
            )
        }
        Log.d("vefvefvefdvevevferve", activity.toString())
    }

    private fun onError(error: ErrorHandler) {
        _state.update {
            it.copy(
                isLoading = false,
                isError = true,
                error = error
            )
        }
    }

    override fun onClickNextStartScreen() {
        _state.update { it.copy(isStartScreenVisible = false) }
    }

    override fun onClickNextActivity() {
        _state.update { it.copy(isStartScreenVisible = true) }
    }

    override fun onClickNextFinishScreen() {
        sendEffect(ActivityUIEffect.OnClickBack)
    }

    override fun onClickBack() {
        sendEffect(ActivityUIEffect.OnClickBack)
    }

}