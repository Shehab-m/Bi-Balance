package com.biBalance.myapplication.presentation.todo

import com.biBalance.myapplication.presentation.base.BaseInteractionListener

interface TodoInteractionListener: BaseInteractionListener {
    fun onClickBack()
    fun onGoalInputChange(text: String)
    fun onActivityChange(text: String)
    fun onEventInputChange(text: String)
    fun onMedsInputChange(text: String)
    fun onClickSaveTasks()
}