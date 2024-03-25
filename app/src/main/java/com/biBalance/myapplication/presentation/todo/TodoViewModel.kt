package com.biBalance.myapplication.presentation.todo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.viewModelScope
import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.data.source.remote.model.Tasks
import com.biBalance.myapplication.presentation.base.BaseViewModel
import com.biBalance.myapplication.util.getCurrentFormattedDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<TodoUIState, TodoUIEffect>(TodoUIState()),
    TodoInteractionListener {

    init {
        getTasksForDate()
    }

    override fun onClickSaveTasks() {
        viewModelScope.launch {
            repository.storeTasks(
                _state.value.goalState,
                _state.value.medsState,
                _state.value.activityState,
                _state.value.eventState,
            )
        }
        sendEffect(TodoUIEffect.OnClickSaveTasks)
    }

    override fun onClickBack() {
        sendEffect(TodoUIEffect.OnClickBack)
    }

    override fun onGoalInputChange(text: String) {
        _state.update { it.copy(goalState = text) }
    }

    override fun onActivityChange(text: String) {
        _state.update { it.copy(activityState = text) }
    }

    override fun onEventInputChange(text: String) {
        _state.update { it.copy(eventState = text) }
    }

    override fun onMedsInputChange(text: String) {
        _state.update { it.copy(medsState = text) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getTasksForDate() {
        val todayDate = getCurrentFormattedDate()
        tryToExecute(
            { repository.getTasksForDate(todayDate) },
            ::onGetTasksForDateSuccess,
            ::onError
        )
    }

    private fun onGetTasksForDateSuccess(tasks: Tasks?) {
        updateState {
            it.copy(
                goalState = tasks?.tasks?.get(0)?.task1 ?: "",
                medsState = tasks?.tasks?.get(0)?.task2 ?: "",
                activityState = tasks?.tasks?.get(0)?.task3 ?: "",
                eventState = tasks?.tasks?.get(0)?.task4 ?: "",
                isLoading = false
            )
        }
    }

    private fun onError(error: Exception) {
        _state.update { it.copy(isLoading = false) }
    }

}