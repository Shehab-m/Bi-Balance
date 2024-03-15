package com.biBalance.myapplication.presentation.todo

import com.biBalance.myapplication.data.repository.BiBalanceRepository
import com.biBalance.myapplication.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val repository: BiBalanceRepository
) : BaseViewModel<TodoUIState, TodoUIEffect>(TodoUIState()),
    TodoInteractionListener {

    init {
        getUserData()
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

    private fun getUserData() {
    }


}