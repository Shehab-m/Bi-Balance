package com.biBalance.myapplication.presentation.todo

data class TodoUIState(
    val isLoading: Boolean = false,
    val goalState: String = "",
    val medsState: String = "",
    val activityState: String = "",
    val eventState: String = "",
)